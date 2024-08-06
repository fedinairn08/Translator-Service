package com.tinkoff.translater.service;

import com.tinkoff.translater.dao.TranslationDAO;
import com.tinkoff.translater.dao.UserDAO;
import com.tinkoff.translater.entity.TranslationDetails;
import com.tinkoff.translater.entity.User;
import com.tinkoff.translater.helper.DateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class TranslatorService {
	private static final Logger logger = Logger.getLogger(TranslatorService.class.getName());
	private final TranslationDAO translationDAO;
	private final UserDAO userDAO;
	private final TranslatorAPIService translatorAPIService;
	private final ExecutorService executorService;

	/**
	 * Translates the text into the specified language, writes the translation details to the database
	 * @param toLanguage target translation language
	 * @param text text to translate
	 * @param ip ip to write to database
	 * @return translated text
	 */
	public String translateText(String toLanguage, String sourceLanguage, String text, String ip) {
		Timestamp requestDate = DateHelper.getCurrentTimestamp();
		String[] words = text.split(" ");
		List<Future<String>> futures = new ArrayList<>();

		for (String word : words) {
			futures.add(executorService.submit(() -> translatorAPIService.translateWord(word, toLanguage, sourceLanguage)));
		}

		StringBuilder translatedTextBuilder = new StringBuilder();
		for (Future<String> future : futures) {
			try {
				translatedTextBuilder.append(future.get()).append(" ");
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Error translating word", e);
				return "http 400 Ошибка перевода";
			}
		}

		String translatedText = translatedTextBuilder.toString().trim();

		User user = userDAO.save(new User(null, ip));
		translationDAO.save(new TranslationDetails(null, text, translatedText, toLanguage, sourceLanguage, requestDate, user));

		return "http 200 " + translatedText;
	}
}

package com.tinkoff.translater.service;

import com.tinkoff.translater.config.YandexAPIConfig;
import com.tinkoff.translater.dto.request.YandexTranslationRequestDTO;
import com.tinkoff.translater.dto.response.YandexResultTranslationResponseDTO;
import com.tinkoff.translater.dto.response.YandexTextTranslationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.logging.Logger;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TranslatorAPIService {
	private static final Logger logger = Logger.getLogger(TranslatorAPIService.class.getName());
	private final YandexAPIConfig yandexAPIConfig;
	private final RestTemplate restTemplate;

	/**
	 * Translates a single word into the specified language through a request to Yandex api
	 * @param word word to translate
	 * @param toLanguage target translation language
	 * @param sourceLanguage source language
	 * @return translated word
	 */
	public String translateWord(String word, String toLanguage, String sourceLanguage) throws Exception {
		YandexTranslationRequestDTO requestDTO = new YandexTranslationRequestDTO(yandexAPIConfig.getFolderId(),
				List.of(word), toLanguage, sourceLanguage);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + yandexAPIConfig.getToken());
		headers.set("Accept", "application/json");
		HttpEntity<YandexTranslationRequestDTO> requestEntity = new HttpEntity<>(requestDTO, headers);

		ResponseEntity<YandexResultTranslationResponseDTO> responseEntity = restTemplate.exchange(
				yandexAPIConfig.getApiTranslationUrl(),
				HttpMethod.POST,
				requestEntity,
				YandexResultTranslationResponseDTO.class
		);

		YandexResultTranslationResponseDTO responseDTO = responseEntity.getBody();
		assert responseDTO != null;
		return responseDTO.getTranslations().stream()
				.map(YandexTextTranslationDTO::getText)
				.findFirst().orElse("");
	}
}

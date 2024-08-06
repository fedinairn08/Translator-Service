package com.tinkoff.translater.dao.implementation;

import com.tinkoff.translater.dao.TranslationDAO;
import com.tinkoff.translater.entity.TranslationDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class JDBCTranslationDAO implements TranslationDAO {
	private final JdbcTemplate jdbcTemplate;

	@Override
	public void save(TranslationDetails translationDetails) {
		String query = "INSERT INTO translation_details " +
				"(source_text, translated_text, to_language, source_language, request_date, user_id) " +
				"VALUES (?, ?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, translationDetails.getSourceText());
			ps.setString(2, translationDetails.getTranslatedText());
			ps.setString(3, translationDetails.getToLanguage());
			ps.setString(4, translationDetails.getSourceLanguage());
			ps.setTimestamp(5, translationDetails.getRequestDate());
			ps.setLong(6, translationDetails.getUser().getId());

			return ps;
		}, keyHolder);

		Objects.requireNonNull(keyHolder.getKey()).longValue();
	}
}

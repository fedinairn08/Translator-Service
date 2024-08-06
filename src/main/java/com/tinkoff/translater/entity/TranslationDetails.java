package com.tinkoff.translater.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslationDetails {
	private Long id;
	private String sourceText;
	private String translatedText;
	private String toLanguage;
	private String sourceLanguage;
	private Timestamp requestDate;
	private User user;
}

package com.tinkoff.translater.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslateTextRequestDTO {
	private String toLanguage;
	private String sourceLanguage;
	private String text;
}

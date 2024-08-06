package com.tinkoff.translater.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YandexGettingIAMTokenRequestDTO {
	private String yandexPassportOauthToken;
}

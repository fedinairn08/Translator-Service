package com.tinkoff.translater.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "yandex")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YandexAPIConfig {
	private String token;
	private String folderId;
	private String OAuthToken;
	private final String apiTranslationUrl = "https://translate.api.cloud.yandex.net/translate/v2/translate";
	private final String apiGettingTokenUrl = "https://iam.api.cloud.yandex.net/iam/v1/tokens";
}

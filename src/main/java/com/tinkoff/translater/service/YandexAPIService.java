package com.tinkoff.translater.service;

import com.tinkoff.translater.config.YandexAPIConfig;
import com.tinkoff.translater.dto.request.YandexGettingIAMTokenRequestDTO;
import com.tinkoff.translater.dto.response.YandexIAMTokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class YandexAPIService {
	private final YandexAPIConfig yandexAPIConfig;
	private final RestTemplate restTemplate;

	/**
	 * Requests an IAM token
	 * @return IAM token
	 */

	public String getYandexIAMToken() {
		YandexGettingIAMTokenRequestDTO requestDTO = new YandexGettingIAMTokenRequestDTO(yandexAPIConfig.getOAuthToken());

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		HttpEntity<YandexGettingIAMTokenRequestDTO> requestEntity = new HttpEntity<>(requestDTO, headers);

		ResponseEntity<YandexIAMTokenResponseDTO> responseEntity = restTemplate.exchange(
				yandexAPIConfig.getApiGettingTokenUrl(),
				HttpMethod.POST,
				requestEntity,
				YandexIAMTokenResponseDTO.class
		);

		YandexIAMTokenResponseDTO responseDTO = responseEntity.getBody();

        assert responseDTO != null;
        return responseDTO.getIamToken();
	}
}

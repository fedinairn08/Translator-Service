package com.tinkoff.translater.initialize;

import com.tinkoff.translater.config.YandexAPIConfig;
import com.tinkoff.translater.service.YandexAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {
	private final YandexAPIConfig yandexAPIConfig;
	private final YandexAPIService yandexAPIService;

	@Override
	public void run(String... args) {
		String token = yandexAPIService.getYandexIAMToken();
		System.out.println("IAM-токен: " + token);
		yandexAPIConfig.setToken(token);
	}
}
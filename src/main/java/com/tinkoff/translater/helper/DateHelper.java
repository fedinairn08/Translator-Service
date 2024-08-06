package com.tinkoff.translater.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateHelper {
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
}

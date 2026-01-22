package ru.yandex.practicum.sleeptracker;

public enum SleepQuality {
	GOOD,
	NORMAL,
	BAD;

	static SleepQuality parse(String value) {
		for (SleepQuality quality : SleepQuality.values()) {
			if (quality.name().equalsIgnoreCase(value)) {
				return quality;
			}
		}
		throw new IllegalArgumentException("Invalid SleepQuality: " + value);
	}
}

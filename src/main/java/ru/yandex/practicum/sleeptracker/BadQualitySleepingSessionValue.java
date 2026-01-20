package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class BadQualitySleepingSessionValue implements Function<List<SleepingSession>, Long> {

	@Override
	public Long apply(List<SleepingSession> sleepingSessions) {
		return sleepingSessions.stream()
				.filter(session -> session.qualityOfSleep.equals(SleepQuality.BAD))
				.count();
	}
}

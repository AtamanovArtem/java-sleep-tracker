package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class MaxDurationSleepingSession implements Function<List<SleepingSession>, Long> {

	@Override
	public Long apply(List<SleepingSession> sleepingSessions) {
		return sleepingSessions.stream()
				.map(session -> session.periodOfSleepingSession(session.startTime, session.finishTime))
				.max(Long::compare)
				.orElseThrow(() -> new IllegalArgumentException("Список сессий пуст"));
	}
}

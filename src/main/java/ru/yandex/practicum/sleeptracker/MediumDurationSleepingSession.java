package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MediumDurationSleepingSession implements Function<List<SleepingSession>, Double> {

	@Override
	public Double apply(List<SleepingSession> sleepingSessions) {
		return sleepingSessions.stream()
				.map(session -> session.periodOfSleepingSession(session.startTime, session.finishTime))
				.collect(Collectors.averagingLong(Long::longValue));
	}
}

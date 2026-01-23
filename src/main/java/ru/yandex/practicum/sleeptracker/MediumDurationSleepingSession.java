package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MediumDurationSleepingSession implements Function<List<SleepingSession>, SleepAnalysisResult<?>> {

	@Override
	public SleepAnalysisResult<Double> apply(List<SleepingSession> sleepingSessions) {
		Double result = sleepingSessions.stream()
				.map(session -> session.periodOfSleepingSession(session.startTime, session.finishTime))
				.collect(Collectors.averagingLong(Long::longValue));
		SleepAnalysisResult<Double> sleepAnalysisResult = new SleepAnalysisResult<>(result);
		sleepAnalysisResult.setDescription("Средняя продолжительность сессии сна в минутах");

		return sleepAnalysisResult;
	}
}

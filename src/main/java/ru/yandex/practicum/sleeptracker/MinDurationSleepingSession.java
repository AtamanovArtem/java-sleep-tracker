package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class MinDurationSleepingSession implements Function<List<SleepingSession>, SleepAnalysisResult<?>> {

	@Override
	public SleepAnalysisResult<Long> apply(List<SleepingSession> sleepingSessions) {
		Long result = sleepingSessions.stream()
				.map(session -> session.periodOfSleepingSession(session.startTime, session.finishTime))
				.min(Long::compare)
				.orElseThrow(() -> new IllegalArgumentException("Список сессий пуст"));
		SleepAnalysisResult<Long> sleepAnalysisResult = new SleepAnalysisResult<>(result);
		sleepAnalysisResult.setDescription("Минимальная продолжительность сессии сна в минутах");

		return sleepAnalysisResult;
	}
}

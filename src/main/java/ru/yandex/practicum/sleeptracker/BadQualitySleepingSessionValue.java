package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class BadQualitySleepingSessionValue implements Function<List<SleepingSession>, SleepAnalysisResult<?>> {

	@Override
	public SleepAnalysisResult<Long> apply(List<SleepingSession> sleepingSessions) {
		Long result = sleepingSessions.stream()
				.filter(session -> session.qualityOfSleep.equals(SleepQuality.BAD))
				.count();

		SleepAnalysisResult<Long> sleepAnalysisResult = new SleepAnalysisResult<>(result);
		sleepAnalysisResult.setDescription("Количество сессий с плохим качеством");

		return sleepAnalysisResult;
	}
}

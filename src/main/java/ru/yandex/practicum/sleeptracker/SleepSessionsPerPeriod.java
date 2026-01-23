package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class SleepSessionsPerPeriod implements Function<List<SleepingSession>, SleepAnalysisResult<?>> {

	@Override
	public SleepAnalysisResult<Integer> apply(List<SleepingSession> sleepingSessions) {
		Integer result = sleepingSessions.size();
		SleepAnalysisResult<Integer> sleepAnalysisResult = new SleepAnalysisResult<>(result);
		sleepAnalysisResult.setDescription("Количество сессий сна за период");

		return sleepAnalysisResult;
	}
}

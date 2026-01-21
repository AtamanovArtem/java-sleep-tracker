package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleeplessNightAnalyzer implements Function<List<SleepingSession>, SleepAnalysisResult<?>> {


	@Override
	public SleepAnalysisResult<Long> apply(List<SleepingSession> sleepingSessions) {
		Long result = sleepingSessions.stream()
				.collect(Collectors.groupingBy(session -> session.startTime.toLocalDate()))
				.values().stream()
				.filter(this::isSleeplessNight)
				.count();
		SleepAnalysisResult<Long> sleepAnalysisResult = new SleepAnalysisResult<>(result);
		sleepAnalysisResult.setDescription("Количество бессонных ночей");

		return sleepAnalysisResult;
	}

	private boolean isSleeplessNight(List<SleepingSession> sessions) {
		return !sessions.stream()
				.anyMatch(session -> {
					LocalDateTime start = session.startTime;
					LocalDateTime end = session.finishTime;
					return (start.getHour() < 6 && end.getHour() >= 6) || (start.getHour() <= 6 && end.getHour() > 6);
				});
	}
}

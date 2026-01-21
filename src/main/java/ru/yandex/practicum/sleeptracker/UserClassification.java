package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserClassification implements Function<List<SleepingSession>, SleepAnalysisResult<?>> {

	@Override
	public SleepAnalysisResult<UserType> apply(List<SleepingSession> sleepingSessions) {
		List<Long> sova = new ArrayList<>();
		List<Long> javoronok = new ArrayList<>();
		List<Long> golub = new ArrayList<>();

		Map<LocalDate, List<SleepingSession>> sessionsGroupedByDate = sleepingSessions.stream()
				.collect(Collectors.groupingBy(session -> session.startTime.toLocalDate()));

		sessionsGroupedByDate.forEach((date, sessions) -> {
			long sovaCount = sessions.stream()
					.filter(this::isSova)
					.count();

			long javoronokCount = sessions.stream()
					.filter(this::isJavoronok)
					.count();

			long golubCount = sessions.stream()
					.filter(session -> !isSova(session) && !isJavoronok(session))
					.count();

			if (sovaCount > javoronokCount && sovaCount > golubCount) {
				sova.add(sovaCount);

			} else if (javoronokCount > sovaCount && javoronokCount > golubCount) {
				javoronok.add(javoronokCount);
			} else {
				golub.add(golubCount);
			}
		});

		UserType result;
		if (sova.size() > javoronok.size() && sova.size() > golub.size()) {
			result = UserType.SOVA;
		} else if (javoronok.size() > sova.size() && javoronok.size() > golub.size()) {
			result = UserType.JAVORONOK;
		} else {
			result = UserType.GOLUB;
		}

		SleepAnalysisResult<UserType> sleepAnalysisResult = new SleepAnalysisResult<>(result);
		sleepAnalysisResult.setDescription("Определенный тип пользователя на основе анализа сессий сна");

		return sleepAnalysisResult;

	}

	private boolean isSova(SleepingSession session) {
		return session.startTime.getHour() > 9 && session.finishTime.getHour() > 23;
	}

	private boolean isJavoronok(SleepingSession session) {
		return session.startTime.getHour() < 7 && session.finishTime.getHour() < 22;
	}
}

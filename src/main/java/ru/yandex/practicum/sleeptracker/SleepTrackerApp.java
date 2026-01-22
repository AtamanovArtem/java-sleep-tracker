package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.*;
import java.util.function.Function;

public class SleepTrackerApp {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Пожалуйста, укажите путь к файлу с логом сна.");
			return;
		}

		String fileName = args[0];
		List<SleepingSession> sleepingSessions = LogFileReader.readLogFile(fileName);

		List<Function<List<SleepingSession>, SleepAnalysisResult<?>>> functions = List.of(
				new BadQualitySleepingSessionValue(),
				new MaxDurationSleepingSession(),
				new MediumDurationSleepingSession(),
				new MinDurationSleepingSession(),
				new SleepSessionsPerPeriod(),
				new UserClassification()
		);

		functions.forEach(function -> {
			SleepAnalysisResult<?> result = function.apply(sleepingSessions);
			System.out.println(result.getDescription() + ": " + result.getResult());
		});
	}
}



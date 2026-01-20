package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

import static java.util.List.*;

public class SleepTrackerApp {

	public static void main(String[] args) {
		LogFileReader reader = new LogFileReader("sleep_log.txt");
		if (args.length == 0) {
			System.err.println("Пожалуйста, укажите путь к файлу с логом сна.");
			return;
		}

		String fileName = args[0];
		List<SleepingSession> sleepingSessions = LogFileReader.readLogFile(fileName);



		List<Function> functions = of(
				new SleepSessionsPerPeriod(),//нужно еще завернуть в обертку чтобы с текстовым выражением!
				new MinDurationSleepingSession(),
				new MaxDurationSleepingSession(),
				new MediumDurationSleepingSession(),
				new BadQualitySleepingSessionValue()

		);
	}


}
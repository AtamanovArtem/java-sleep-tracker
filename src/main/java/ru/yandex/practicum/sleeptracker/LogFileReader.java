package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogFileReader {
	public String fileName;


	protected LogFileReader(String fileName) {
		this.fileName = fileName;
	}

	protected static List<SleepingSession> readLogFile(String fileName) {
		List<SleepingSession> sleepingSessions = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			br.lines()
					.forEach(line -> {
						String[] parts = line.split(";");
						if (parts.length == 3) {
							LocalDateTime startTime = LocalDateTime.parse(parts[0]);
							LocalDateTime endTime = LocalDateTime.parse(parts[1]);
							SleepQuality qualityOfSleep = SleepQuality.parse(parts[2]);

							SleepingSession session = new SleepingSession(startTime, endTime, qualityOfSleep);
							sleepingSessions.add(session);
						}
					});
		} catch (IOException e) {
			System.err.println("Файл не найден");
		}
		return sleepingSessions;
	}
}

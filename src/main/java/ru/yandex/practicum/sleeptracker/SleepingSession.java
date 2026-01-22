package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepingSession {
	LocalDateTime startTime;
	LocalDateTime finishTime;
	SleepQuality qualityOfSleep;

	protected SleepingSession(LocalDateTime startTime, LocalDateTime finishTime, SleepQuality qualityOfSleep) {
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.qualityOfSleep = qualityOfSleep;
	}

	protected long periodOfSleepingSession(LocalDateTime startTime, LocalDateTime finishTime) {
		Duration duration = Duration.between(startTime, finishTime);
		return duration.toMinutes();

	}

}

package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepTrackerAppTest {
	@Test
	public void badQualitySleepingSessionValueTest() {

		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 22, 0),
				LocalDateTime.of(2025, 10, 2, 7, 30), SleepQuality.GOOD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 40),
				LocalDateTime.of(2025, 10, 4, 8, 0), SleepQuality.BAD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 13, 30),
				LocalDateTime.of(2025, 10, 5, 14, 15), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 11, 23, 10),
				LocalDateTime.of(2025, 10, 12, 7, 0), SleepQuality.BAD));

		BadQualitySleepingSessionValue test = new BadQualitySleepingSessionValue();
		SleepAnalysisResult<Long> result = test.apply(sleepingSessions);
		assertEquals(2, result.getResult());
	}

	@Test
	public void badQualitySleepingSessionValueWithoutBADTest() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 22, 0),
				LocalDateTime.of(2025, 10, 2, 7, 30), SleepQuality.GOOD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 40),
				LocalDateTime.of(2025, 10, 4, 8, 0), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 13, 30),
				LocalDateTime.of(2025, 10, 5, 14, 15), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 11, 23, 10),
				LocalDateTime.of(2025, 10, 12, 7, 0), SleepQuality.GOOD));

		BadQualitySleepingSessionValue test = new BadQualitySleepingSessionValue();
		SleepAnalysisResult<Long> result = test.apply(sleepingSessions);
		assertEquals(0, result.getResult());
	}

	@Test
	public void maxDurationSleepingSessionTest() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 22, 0),
				LocalDateTime.of(2025, 10, 2, 8, 0), SleepQuality.GOOD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 00),
				LocalDateTime.of(2025, 10, 4, 6, 0), SleepQuality.NORMAL));
		MaxDurationSleepingSession test = new MaxDurationSleepingSession();
		SleepAnalysisResult<Long> result = test.apply(sleepingSessions);
		assertEquals(600, result.getResult());
	}

	@Test
	public void maxDurationSleepingSessionTestWithEqualsDurationsTest() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 22, 0),
				LocalDateTime.of(2025, 10, 2, 8, 0), SleepQuality.GOOD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 00),
				LocalDateTime.of(2025, 10, 4, 9, 0), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 13, 30),
				LocalDateTime.of(2025, 10, 5, 14, 15), SleepQuality.NORMAL));
		MaxDurationSleepingSession test = new MaxDurationSleepingSession();
		SleepAnalysisResult<Long> result = test.apply(sleepingSessions);
		assertEquals(600, result.getResult());
	}

	@Test
	public void minDurationSleepingSessionTest() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 22, 0),
				LocalDateTime.of(2025, 10, 2, 8, 0), SleepQuality.GOOD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 00),
				LocalDateTime.of(2025, 10, 4, 9, 0), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 13, 00),
				LocalDateTime.of(2025, 10, 5, 14, 0), SleepQuality.NORMAL));
		MinDurationSleepingSession test = new MinDurationSleepingSession();
		SleepAnalysisResult<Long> result = test.apply(sleepingSessions);
		assertEquals(60, result.getResult());
	}

	@Test
	public void minDurationSleepingSessionTestWithEqualsDuration() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 22, 0),
				LocalDateTime.of(2025, 10, 2, 8, 0), SleepQuality.GOOD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 00),
				LocalDateTime.of(2025, 10, 4, 9, 0), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 1, 00),
				LocalDateTime.of(2025, 10, 5, 14, 0), SleepQuality.NORMAL));
		MinDurationSleepingSession test = new MinDurationSleepingSession();
		SleepAnalysisResult<Long> result = test.apply(sleepingSessions);
		assertEquals(600, result.getResult());
	}

	@Test
	public void mediumDurationSleepingSessionTest() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 22, 0),
				LocalDateTime.of(2025, 10, 2, 8, 0), SleepQuality.GOOD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 00),
				LocalDateTime.of(2025, 10, 4, 9, 0), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 13, 00),
				LocalDateTime.of(2025, 10, 5, 18, 0), SleepQuality.NORMAL));
		MediumDurationSleepingSession test = new MediumDurationSleepingSession();
		SleepAnalysisResult<Double> result = test.apply(sleepingSessions);
		assertEquals(500, result.getResult());
	}

	@Test
	public void mediumDurationSleepingSessionTestWithDoubleResult() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 22, 30),
				LocalDateTime.of(2025, 10, 2, 8, 0), SleepQuality.GOOD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 30),
				LocalDateTime.of(2025, 10, 4, 9, 0), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 13, 13),
				LocalDateTime.of(2025, 10, 5, 17, 0), SleepQuality.NORMAL));
		MediumDurationSleepingSession test = new MediumDurationSleepingSession();
		SleepAnalysisResult<Double> result = test.apply(sleepingSessions);
		assertEquals(455.6666666666667, result.getResult());
	}

	@Test
	public void periodOfSleepingSessionTest() {
		LocalDateTime startTime = LocalDateTime.of(2025, 10, 1, 22, 30);
		LocalDateTime finishTime = LocalDateTime.of(2025, 10, 2, 8, 0);

		SleepingSession session = new SleepingSession(startTime, finishTime, SleepQuality.GOOD);

		Duration duration = Duration.between(startTime, finishTime);
		long minutes = duration.toMinutes();

		assertEquals(570, minutes);
	}

	@Test
	public void sleeplessNightAnalyzerTestWithOneSleeplessNight() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 22, 0),
				LocalDateTime.of(2025, 10, 2, 7, 30), SleepQuality.GOOD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 40),
				LocalDateTime.of(2025, 10, 4, 8, 0), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 18, 30),
				LocalDateTime.of(2025, 10, 5, 23, 30), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 11, 23, 10),
				LocalDateTime.of(2025, 10, 12, 7, 0), SleepQuality.GOOD));

		SleeplessNightAnalyzer test = new SleeplessNightAnalyzer();
		SleepAnalysisResult<Long> result = test.apply(sleepingSessions);
		assertEquals(1, result.getResult());
	}

	@Test
	public void sleeplessNightAnalyzerTestMonthEnd() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 22, 0),
				LocalDateTime.of(2025, 10, 2, 7, 30), SleepQuality.GOOD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 40),
				LocalDateTime.of(2025, 10, 4, 8, 0), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 18, 30),
				LocalDateTime.of(2025, 10, 5, 23, 30), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 31, 23, 10),
				LocalDateTime.of(2025, 11, 1, 7, 0), SleepQuality.GOOD));

		SleeplessNightAnalyzer test = new SleeplessNightAnalyzer();
		SleepAnalysisResult<Long> result = test.apply(sleepingSessions);
		assertEquals(1, result.getResult());
	}

	@Test
	public void sleeplessNightAnalyzerTestBoundaryValues() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 2, 0),
				LocalDateTime.of(2025, 10, 1, 5, 30), SleepQuality.GOOD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 0, 0),
				LocalDateTime.of(2025, 10, 4, 5, 59), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 4, 23, 59),
				LocalDateTime.of(2025, 10, 5, 3, 30), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 31, 23, 10),
				LocalDateTime.of(2025, 11, 1, 7, 0), SleepQuality.GOOD));

		SleeplessNightAnalyzer test = new SleeplessNightAnalyzer();
		SleepAnalysisResult<Long> result = test.apply(sleepingSessions);
		assertEquals(0, result.getResult());
	}

	@Test
	public void sleepSessionsPerPeriodTest() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 22, 0),
				LocalDateTime.of(2025, 10, 2, 8, 0), SleepQuality.GOOD));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 00),
				LocalDateTime.of(2025, 10, 4, 9, 0), SleepQuality.NORMAL));
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 1, 00),
				LocalDateTime.of(2025, 10, 5, 14, 0), SleepQuality.NORMAL));
		SleepSessionsPerPeriod test = new SleepSessionsPerPeriod();
		SleepAnalysisResult<Integer> result = test.apply(sleepingSessions);
		assertEquals(3, result.getResult());
	}

	@Test
	public void userClassificationTestJavoronok() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 21, 59),
				LocalDateTime.of(2025, 10, 2, 6, 59), SleepQuality.GOOD));//Javoronok
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 30),
				LocalDateTime.of(2025, 10, 4, 9, 30), SleepQuality.NORMAL));//Sova
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 21, 00),
				LocalDateTime.of(2025, 10, 6, 6, 0), SleepQuality.NORMAL));//Javoronok
		UserClassification test = new UserClassification();
		SleepAnalysisResult<UserType> result = test.apply(sleepingSessions);
		assertEquals(UserType.JAVORONOK, result.getResult());
	}

	@Test
	public void userClassificationTestSova() {
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 21, 59),
				LocalDateTime.of(2025, 10, 2, 6, 59), SleepQuality.GOOD));//Javoronok
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 30),
				LocalDateTime.of(2025, 10, 4, 9, 30), SleepQuality.NORMAL));//Sova
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 5, 23, 30),
				LocalDateTime.of(2025, 10, 6, 10, 0), SleepQuality.NORMAL));//Sova
		UserClassification test = new UserClassification();
		SleepAnalysisResult<UserType> result = test.apply(sleepingSessions);
		assertEquals(UserType.SOVA, result.getResult());
	}

	@Test
	public void userClassificationTestGolub() { 
		List<SleepingSession> sleepingSessions = new ArrayList<>();
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 1, 21, 59),
				LocalDateTime.of(2025, 10, 2, 6, 59), SleepQuality.GOOD));//Javoronok
		sleepingSessions.add(new SleepingSession(LocalDateTime.of(2025, 10, 3, 23, 30),
				LocalDateTime.of(2025, 10, 4, 9, 30), SleepQuality.NORMAL));//Sova

		UserClassification test = new UserClassification();
		SleepAnalysisResult<UserType> result = test.apply(sleepingSessions);
		assertEquals(UserType.GOLUB, result.getResult());
	}
}

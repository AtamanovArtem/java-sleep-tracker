package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult<T> {
	private T result;
	private String description;

	public SleepAnalysisResult(T result) {
		this.result = result;
	}

	public T getResult() {
		return result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

package at.reisisoft.jku.ce.adaptivelearning.core.engine;

import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;

public class HistoryEntry {

	public final boolean isCorrect;
	public final double difficulty;

	public HistoryEntry(IQuestion<?> question) {
		difficulty = question.getDifficulty();
		isCorrect = question.checkUserAnswer();

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(difficulty);
		result = prime * result + (int) (temp ^ temp >>> 32);
		result = prime * result + (isCorrect ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		HistoryEntry other = (HistoryEntry) obj;
		if (Double.doubleToLongBits(difficulty) != Double
				.doubleToLongBits(other.difficulty)) {
			return false;
		}
		if (isCorrect != other.isCorrect) {
			return false;
		}
		return true;
	}
}

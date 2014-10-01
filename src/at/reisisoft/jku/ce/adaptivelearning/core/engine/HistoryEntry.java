package at.reisisoft.jku.ce.adaptivelearning.core.engine;

import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;
import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;

public class HistoryEntry {

	public final double points;
	public final double difficulty;
	public final IQuestion<? extends AnswerStorage> question;

	public HistoryEntry(IQuestion<? extends AnswerStorage> question) {
		points = question.checkUserAnswer();
		difficulty = question.getDifficulty();
		this.question = question;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(difficulty);
		result = prime * result + (int) (temp ^ temp >>> 32);
		temp = Double.doubleToLongBits(points);
		result = prime * result + (int) (temp ^ temp >>> 32);
		result = prime * result + (question == null ? 0 : question.hashCode());
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
		if (Double.doubleToLongBits(points) != Double
				.doubleToLongBits(other.points)) {
			return false;
		}
		if (question == null) {
			if (other.question != null) {
				return false;
			}
		} else if (!question.equals(other.question)) {
			return false;
		}
		return true;
	}
}

package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;

public class ProfitDataStorage extends AnswerStorage {

	private static final long serialVersionUID = 3127620913952575646L;
	private ProfitPossibleAnswers value;

	public ProfitDataStorage() {
		value = null;
	}

	public ProfitDataStorage(ProfitPossibleAnswers value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value == null ? "NULL" : value.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (value == null ? 0 : value.hashCode());
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
		ProfitDataStorage other = (ProfitDataStorage) obj;
		if (value != other.value) {
			return false;
		}
		return true;
	}

	public ProfitPossibleAnswers getValue() {
		return value;
	}

	public void setValue(ProfitPossibleAnswers value) {
		if (value != null) {
			this.value = value;
		}
	}

}
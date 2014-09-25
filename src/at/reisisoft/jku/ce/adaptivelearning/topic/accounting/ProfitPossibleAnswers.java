package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

public enum ProfitPossibleAnswers {
	Increase("Gewinnerhöhend"), Decrease("Gewinnmindernd"), NO_Change(
			"Beeinflusst den Gewinn nicht");
	String name;

	private ProfitPossibleAnswers(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}

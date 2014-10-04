package at.reisisoft.jku.ce.adaptivelearning.core.engine;

import java.util.List;

public class ResultFiredArgs {
	// TODO implement
	public final boolean outOfQuestions;
	public final List<HistoryEntry> history;
	public final double skillLevel;
	public final double delta;

	public ResultFiredArgs(boolean outOfQuestions, List<HistoryEntry> history,
			double skillLevel, double delta) {
		super();
		this.outOfQuestions = outOfQuestions;
		this.history = history;
		this.skillLevel = skillLevel;
		this.delta = delta;
	}

}

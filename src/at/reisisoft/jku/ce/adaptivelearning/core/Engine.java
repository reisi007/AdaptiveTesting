package at.reisisoft.jku.ce.adaptivelearning.core;

import java.util.Stack;

/**
 * The worker class
 *
 * @author Florian
 *
 */
public class Engine<Question extends IQuestion<? extends IAnswerStorage>> {
	private Stack<HistoryEntry> history = new Stack<>();

	public class HistoryEntry {
		public final boolean isCorrect;
		public final double difficulty;

		public HistoryEntry(Question question) {
			difficulty = question.getDifficulty();
			isCorrect = question.checkUserAnswer();
		}
	}
}

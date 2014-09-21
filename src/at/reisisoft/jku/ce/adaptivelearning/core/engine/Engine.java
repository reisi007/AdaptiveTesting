package at.reisisoft.jku.ce.adaptivelearning.core.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;

/**
 * The worker class
 *
 * @author Florian
 *
 */
public class Engine {
	private Stack<HistoryEntry> history = new Stack<>();
	private List<IResultFiredListener> resultFiredListeners = new ArrayList<>();
	private List<ICurrentQuestionChangeListener> currentQuestionChangeListeners = new ArrayList<>();

	public void addQuestionChangeListener(
			ICurrentQuestionChangeListener questionChangeListener) {
		currentQuestionChangeListeners.add(questionChangeListener);
	}

	public void addResultFiredListener(IResultFiredListener resultFiredListener) {
		resultFiredListeners.add(resultFiredListener);
	}

	public void removeQuestionChangeListener(
			ICurrentQuestionChangeListener questionChangeListener) {
		currentQuestionChangeListeners.remove(questionChangeListener);
	}

	public void removeResultFiredListener(
			IResultFiredListener resultFiredListener) {
		resultFiredListeners.remove(resultFiredListener);
	}

	/**
	 *
	 * @param question
	 *            A question, answered by the user
	 * @return True if the question is answered correctly, false if not
	 */
	private boolean addQuestionToHistory(IQuestion<?> question) {
		HistoryEntry entry = new HistoryEntry(question);
		history.push(entry);
		return entry.isCorrect;
	}

}

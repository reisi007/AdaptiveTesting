package at.reisisoft.jku.ce.adaptivelearning.core.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;
import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

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
	private final float[] upperBounds;
	private final List<IQuestion<? extends AnswerStorage>>[] bags;
	private int questionNumber;

	/**
	 * Using -1.6f, -0.2f, 1.2f, 2.5f as explicit upper bounds
	 */
	public Engine() {
		this(-1.6f, -0.2f, 1.2f, 2.5f);
	}

	/**
	 *
	 * @param upperBounds
	 *            : All upper bounds. Additionally a upper bound "+INF" is
	 *            created. All upper bounds including
	 */

	@SuppressWarnings("unchecked")
	public Engine(float... upperBounds) {
		Arrays.sort(upperBounds);
		this.upperBounds = upperBounds;
		bags = new List[upperBounds.length + 1];
		for (int i = 0; i < bags.length; i++) {
			bags[i] = new ArrayList<>();
		}
	}

	/**
	 *
	 * @param question
	 *            The question to add
	 * @return Pool upper bound difficulty
	 */
	public float addQuestionToPool(IQuestion<? extends AnswerStorage> question) {
		if (question != null) {
			questionNumber++;
		}
		for (int i = 0; i < upperBounds.length; i++) {
			if (question.getDifficulty() <= upperBounds[i]) {
				bags[i].add(question);
				return upperBounds[i];
			}
		}
		// If the difficulty is higher than all upper bounds specified
		bags[upperBounds.length].add(question);
		return Float.POSITIVE_INFINITY;
	}

	public void addQuestionChangeListener(
			ICurrentQuestionChangeListener questionChangeListener) {
		assert questionChangeListener != null;
		currentQuestionChangeListeners.add(questionChangeListener);
	}

	public void addResultFiredListener(IResultFiredListener resultFiredListener) {
		assert resultFiredListener != null;
		resultFiredListeners.add(resultFiredListener);
	}

	public void removeQuestionChangeListener(
			ICurrentQuestionChangeListener questionChangeListener) {
		assert questionChangeListener != null;
		currentQuestionChangeListeners.remove(questionChangeListener);
	}

	public void removeResultFiredListener(
			IResultFiredListener resultFiredListener) {
		assert resultFiredListener != null;
		resultFiredListeners.remove(resultFiredListener);
	}

	private void fireQuestionChangeListener(
			IQuestion<? extends AnswerStorage> question) {
		for (ICurrentQuestionChangeListener listener : currentQuestionChangeListeners) {
			listener.questionChanged(question);
		}
	}

	private void fireResultListener(ResultFiredArgs args) {
		for (IResultFiredListener listener : resultFiredListeners) {
			listener.resultFired(args);
		}
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

	public void requestCalculation() {
		// TODO Implement
		Notification
		.show("ERROR - Not implemented",
				"This would check the current question and would promt another one or the result",
				Type.TRAY_NOTIFICATION);
	}

	public void start() {
		fireQuestionChangeListener(getQuestion((upperBounds.length + 1) / 2));

	}

	/**
	 *
	 * @param difficulty
	 *            A upper bound specified during initialisation
	 * @return Question
	 */
	private IQuestion<? extends AnswerStorage> getQuestion(float difficulty) {
		return getQuestion(Arrays.asList(upperBounds).indexOf(difficulty));
	}

	/**
	 *
	 * @param arrayIndex
	 *            An index of the upperBoundArray +1
	 * @return Question
	 */
	private IQuestion<? extends AnswerStorage> getQuestion(int arrayIndex) {
		if (arrayIndex < 0 || arrayIndex > bags.length || questionNumber == 0) {
			return null; // Invalid index or no question available
		}
		List<IQuestion<? extends AnswerStorage>> list = bags[arrayIndex];
		int listSize = list.size();
		if (listSize == 0) {
			int nextArrayIndex = (arrayIndex + 1) % (bags.length + 1);
			return getQuestion(nextArrayIndex);
		}
		int index = (int) Math.round(Math.random() * (listSize - 1));
		IQuestion<? extends AnswerStorage> question = list.get(index);
		list.remove(index);
		questionNumber--;
		// Remove the following line when going live
		Notification.show("Question choosen", "Category " + arrayIndex,
				Type.TRAY_NOTIFICATION);
		return question;
	}
}

package at.reisisoft.jku.ce.adaptivelearning.core.engine.engines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;
import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.HistoryEntry;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.ICurrentQuestionChangeListener;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.IEngine;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.IResultFiredListener;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.ResultFiredArgs;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

/**
 * The worker class
 *
 * @author Florian
 *
 */
public class SimpleEngine implements IEngine {
	private Stack<HistoryEntry> history = new Stack<>();
	private List<IResultFiredListener> resultFiredListeners = new ArrayList<>();
	private List<ICurrentQuestionChangeListener> currentQuestionChangeListeners = new ArrayList<>();
	private final float[] upperBounds;
	private final List<IQuestion<? extends AnswerStorage>>[] bags;
	private int questionNumber;
	private IQuestion<? extends AnswerStorage> question;

	private double[][] getRmatrix() {
		return history.stream()
				.map(e -> new double[] { e.difficulty, e.points })
				.collect(Collectors.toList())
				.toArray(new double[history.size()][]);
	}

	/**
	 * Using -1.6f, -0.2f, 1.2f, 2.5f as explicit upper bounds
	 */
	public SimpleEngine() {
		this(-1.6f, -0.2f, 1.2f, 2.5f);
	}

	/**
	 *
	 * @param upperBounds
	 *            : All upper bounds. Additionally a upper bound "+INF" is
	 *            created. All upper bounds including
	 */

	@SuppressWarnings("unchecked")
	public SimpleEngine(float... upperBounds) {
		Arrays.sort(upperBounds);
		this.upperBounds = upperBounds;
		bags = new List[upperBounds.length + 1];
		for (int i = 0; i < bags.length; i++) {
			bags[i] = new ArrayList<>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.reisisoft.jku.ce.adaptivelearning.core.engine.IEngine#addQuestionToPool
	 * (at.reisisoft.jku.ce.adaptivelearning.core.IQuestion)
	 */
	@Override
	public void addQuestionToPool(IQuestion<? extends AnswerStorage> question) {
		if (question != null) {
			questionNumber++;
		}
		for (int i = 0; i < upperBounds.length; i++) {
			if (question.getDifficulty() <= upperBounds[i]) {
				bags[i].add(question);
				return;
			}
		}
		// If the difficulty is higher than all upper bounds specified
		bags[upperBounds.length].add(question);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.reisisoft.jku.ce.adaptivelearning.core.engine.IEngine#
	 * addQuestionChangeListener
	 * (at.reisisoft.jku.ce.adaptivelearning.core.engine
	 * .ICurrentQuestionChangeListener)
	 */
	@Override
	public void addQuestionChangeListener(
			ICurrentQuestionChangeListener questionChangeListener) {
		assert questionChangeListener != null;
		currentQuestionChangeListeners.add(questionChangeListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.reisisoft.jku.ce.adaptivelearning.core.engine.IEngine#
	 * addResultFiredListener
	 * (at.reisisoft.jku.ce.adaptivelearning.core.engine.IResultFiredListener)
	 */
	@Override
	public void addResultFiredListener(IResultFiredListener resultFiredListener) {
		assert resultFiredListener != null;
		resultFiredListeners.add(resultFiredListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.reisisoft.jku.ce.adaptivelearning.core.engine.IEngine#
	 * removeQuestionChangeListener
	 * (at.reisisoft.jku.ce.adaptivelearning.core.engine
	 * .ICurrentQuestionChangeListener)
	 */
	@Override
	public void removeQuestionChangeListener(
			ICurrentQuestionChangeListener questionChangeListener) {
		assert questionChangeListener != null;
		currentQuestionChangeListeners.remove(questionChangeListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.reisisoft.jku.ce.adaptivelearning.core.engine.IEngine#
	 * removeResultFiredListener
	 * (at.reisisoft.jku.ce.adaptivelearning.core.engine.IResultFiredListener)
	 */
	@Override
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
	private double addQuestionToHistory(
			IQuestion<? extends AnswerStorage> question) {
		HistoryEntry entry = new HistoryEntry(question);
		history.push(entry);
		return entry.points;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.reisisoft.jku.ce.adaptivelearning.core.engine.IEngine#requestCalculation
	 * ()
	 */
	@Override
	public void requestCalculation() {
		// Evaluate current question
		boolean lastCorrect = addQuestionToHistory(question) > 0;
		// Dummy calculation -> get Random next question
		IQuestion<? extends AnswerStorage> nextQuestion = getQuestion(lastCorrect ? 1
				: 0);
		if (nextQuestion == null) {
			ResultFiredArgs args = new ResultFiredArgs(true,
					Collections.unmodifiableList(history), 0);
			fireResultListener(args);
		} else {
			question = nextQuestion;
			fireQuestionChangeListener(nextQuestion);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.reisisoft.jku.ce.adaptivelearning.core.engine.IEngine#start()
	 */
	@Override
	public void start() {
		question = getQuestion((upperBounds.length + 1) / 2 - 1);
		fireQuestionChangeListener(question);

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
			int nextArrayIndex = (arrayIndex + 1) % bags.length;
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

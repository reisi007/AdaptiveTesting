package at.reisisoft.jku.ce.adaptivelearning.core.engine;

import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;
import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;

public interface IEngine {

	/**
	 *
	 * @param question
	 *            The question to add
	 */
	public void addQuestionToPool(IQuestion<? extends AnswerStorage> question);

	/**
	 * 
	 * @param questionChangeListener
	 *            A listener, which listens to change of the question
	 */
	public void addQuestionChangeListener(
			ICurrentQuestionChangeListener questionChangeListener);

	/**
	 * 
	 * @param resultFiredListener
	 *            A listener, which listens to the result of a test run
	 */
	public void addResultFiredListener(IResultFiredListener resultFiredListener);

	/**
	 *
	 * @param questionChangeListener
	 *            A listener, which listens to change of the question
	 */
	public void removeQuestionChangeListener(
			ICurrentQuestionChangeListener questionChangeListener);

	/**
	 *
	 * @param resultFiredListener
	 *            A listener, which listens to the result of a test run
	 */
	public void removeResultFiredListener(
			IResultFiredListener resultFiredListener);

	/**
	 * Signals that the user has finished his input and that the question gets
	 * evaluated
	 */
	public void requestCalculation();

	/**
	 * Starts the test
	 */
	public void start();

}
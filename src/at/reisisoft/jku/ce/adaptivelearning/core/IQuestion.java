package at.reisisoft.jku.ce.adaptivelearning.core;

public interface IQuestion<DataStorage extends IAnswerStorage> {
	/**
	 *
	 * @return The right answer to the question
	 */
	public DataStorage getSolution();

	/**
	 *
	 * @return The answer made by the user. A question, which is read to be
	 *         displayed to the user must return NULL
	 */
	public DataStorage getUserAnswer();

	/**
	 *
	 * @return Checks if the user answer is the, or alternatively, one of the
	 *         right answers
	 */
	public boolean checkUserAnswer();

	/**
	 *
	 * @return The difficulty. 0 the easiest, 1 the hardest
	 */
	public double getDifficulty();
}

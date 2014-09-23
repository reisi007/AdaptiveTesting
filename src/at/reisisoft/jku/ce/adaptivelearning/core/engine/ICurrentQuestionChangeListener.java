package at.reisisoft.jku.ce.adaptivelearning.core.engine;

import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;
import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;

public interface ICurrentQuestionChangeListener {

	public void questionChanged(IQuestion<? extends AnswerStorage> question);

}

package at.reisisoft.jku.ce.adaptivelearning.ui;

import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;
import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class QuestionManager<Question extends Component & IQuestion<? extends AnswerStorage>>
		extends ExtBorderLayout {

	private static final long serialVersionUID = -4764723794449575244L;
	private SingleComponentLayout questionHolder = new SingleComponentLayout();

	public QuestionManager(String quizName) {
		addComponent(questionHolder, Constraint.CENTER);
		StringBuilder labeltext = new StringBuilder();
		// Compose the Label's text
		labeltext.append("<h1 style='text-align: center;'>");
		labeltext.append(quizName);
		labeltext.append("</h1>");
		addComponent(new Label(labeltext.toString(), ContentMode.HTML),
				Constraint.NORTH);
	}

	/**
	 *
	 * @param question
	 *            MUST be of Type Component AND of IQuestion<? extends
	 *            IAnswerStorage>
	 */
	public void addQuestion(Object question) {
		if (!(question instanceof Component && question instanceof IQuestion)
				|| question == null) {
			throw new IllegalArgumentException(
					"question MUST be of Type Component AND of IQuestion<? extends IAnswerStorage>");
		}
		addComponent((Component) question);
	}
}

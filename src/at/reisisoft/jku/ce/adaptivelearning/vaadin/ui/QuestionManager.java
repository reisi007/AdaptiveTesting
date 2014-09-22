package at.reisisoft.jku.ce.adaptivelearning.vaadin.ui;

import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;
import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.Engine;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class QuestionManager extends ExtBorderLayout {

	private static final long serialVersionUID = -4764723794449575244L;
	private SingleComponentLayout questionHolder = new SingleComponentLayout();
	private Engine engine = new Engine();
	private GridLayout southLayout = new GridLayout(3, 1);

	public QuestionManager(String quizName) {
		addComponent(questionHolder, Constraint.CENTER);
		StringBuilder labeltext = new StringBuilder();
		// Compose the Label's text
		labeltext.append("<h1 style='text-align: center;'>");
		labeltext.append(quizName);
		labeltext.append("</h1>");
		addComponent(new Label(labeltext.toString(), ContentMode.HTML),
				Constraint.NORTH);
		addComponent(southLayout, Constraint.SOUTH);
		Button next = new Button("Next question ->");
		southLayout.addComponent(next, 2, 0);
		southLayout.setSizeFull();
		southLayout.setMargin(true);
	}

	/**
	 *
	 * @param question
	 *            A question which is a component as well
	 */
	public <QuestionComponent extends IQuestion<? extends AnswerStorage> & Component> void addQuestion(
			QuestionComponent question) {
		questionHolder.addComponent(question);
	}

	protected final void addHelpButton(Component c) {
		southLayout.addComponent(c, 1, 0);
	}
}

package at.reisisoft.jku.ce.adaptivelearning.vaadin.ui;

import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;
import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.Engine;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.ICurrentQuestionChangeListener;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.IResultFiredListener;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.ResultFiredArgs;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class QuestionManager extends ExtBorderLayout implements
ICurrentQuestionChangeListener, IResultFiredListener {

	private static final long serialVersionUID = -4764723794449575244L;
	private SingleComponentLayout questionHolder = new SingleComponentLayout();
	private final Engine engine = new Engine();
	private GridLayout southLayout = new GridLayout(3, 1);
	private final Button next;

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
		next = new Button("Next question ->");
		next.addClickListener(e -> {
			e.getButton().setEnabled(false);
			engine.requestCalculation();
		});
		southLayout.addComponent(next, 2, 0);
		southLayout.setSizeFull();
		southLayout.setMargin(true);
		// Register to engine events
		engine.addQuestionChangeListener(this);
		engine.addResultFiredListener(this);
	}

	/**
	 *
	 * @param question
	 *            A question which is a component as well
	 */
	public <QuestionComponent extends IQuestion<? extends AnswerStorage> & Component> void addQuestion(
			QuestionComponent question) {
		engine.addQuestionToPool(question);
	}

	protected final void addHelpButton(Component c) {
		southLayout.addComponent(c, 1, 0);
	}

	@Override
	public void resultFired(ResultFiredArgs args) {
		// TODO Implement

	}

	// As QuestionManager only listens to the engine, every Question is a
	// Component as well
	@Override
	public void questionChanged(IQuestion<? extends AnswerStorage> question) {
		// This cast won't fail as every question in the engine is a Component
		// as well
		Component c = (Component) question;
		questionHolder.addComponent(c);
		next.setEnabled(true);
	}

	public void startQuiz() {
		engine.start();
	}
}

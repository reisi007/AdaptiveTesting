package at.reisisoft.jku.ce.adaptivelearning.vaadin.ui;

import at.reisisoft.jku.ce.adaptivelearning.VaadinUI;
import at.reisisoft.jku.ce.adaptivelearning.Views;
import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;
import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.Engine;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.ICurrentQuestionChangeListener;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.IResultFiredListener;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.ResultFiredArgs;
import at.reisisoft.jku.ce.adaptivelearning.html.HtmlLabel;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class QuestionManager extends VerticalLayout implements
		ICurrentQuestionChangeListener, IResultFiredListener, View {

	private static final long serialVersionUID = -4764723794449575244L;
	private SingleComponentLayout questionHolder = new SingleComponentLayout();
	private final Engine engine = new Engine();
	private GridLayout southLayout = new GridLayout(3, 1);
	private final Button next;
	private Component helpComponent = null;
	private Label title;

	public QuestionManager(String quizName) {
		setMargin(true);
		title = HtmlLabel.getCenteredLabel("h1", quizName);
		addComponent(title);
		addComponent(questionHolder);

		addComponent(southLayout);

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
	public <QuestionComponent extends IQuestion<? extends AnswerStorage> & Component & Sizeable> void addQuestion(
			QuestionComponent question) {
		engine.addQuestionToPool(question);
	}

	protected final void addHelpButton(Component c) {
		assert c != null;
		helpComponent = c;
		southLayout.addComponent(helpComponent, 1, 0);
	}

	@Override
	public void resultFired(ResultFiredArgs args) {
		ResultView result = new ResultView(args, title);
		Navigator navigator = getUI().getNavigator();
		assert navigator != null;
		navigator.addView(Views.RESULT.toString(), result);
		navigator.navigateTo(Views.RESULT.toString());

	}

	// As QuestionManager only listens to the engine, every Question is a
	// Component as well
	@Override
	public void questionChanged(IQuestion<? extends AnswerStorage> question) {
		// This cast won't fail as every question in the engine is a Component
		// as well
		Component c = (Component) question;
		questionHolder.addComponent(c);
		Sizeable s = (Sizeable) question;
		s.setSizeFull();
		next.setEnabled(true);
	}

	public void startQuiz() {
		engine.start();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		startQuiz();
		VaadinUI.setCurrentPageTitle(event);
	}
}

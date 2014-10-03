package at.reisisoft.jku.ce.adaptivelearning.vaadin.ui;

import java.lang.reflect.Constructor;
import java.util.List;

import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;
import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;
import at.reisisoft.jku.ce.adaptivelearning.core.IResultView;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.HistoryEntry;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.ResultFiredArgs;
import at.reisisoft.jku.ce.adaptivelearning.html.HtmlLabel;
import at.reisisoft.jku.ce.adaptivelearning.vaadin.ui.core.VaadinUI;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VaadinResultView extends VerticalLayout implements View,
IResultView {

	private static final long serialVersionUID = -6619938011293967055L;

	public VaadinResultView(ResultFiredArgs args, String title) {
		setSpacing(true);
		addComponent(new HtmlLabel(title));
		addComponent(HtmlLabel.getCenteredLabel("h2", "Finished test"));
		addComponent(HtmlLabel.getCenteredLabel("The test ended, because we "
				+ (args.outOfQuestions ? "did not have any more questions"
						: "determined your skill level")));
		addComponent(HtmlLabel
				.getCenteredLabel("This are the difficulties and your answers. On top are your last answers. The first column indicates the difficulty."));
		addComponent(HtmlLabel
				.getCenteredLabel("Closer to -INF means easy question, to +INF dificult questions. This also applies to your skill level!"));

		// Create HTML table of the history
		Table table = new Table();
		final String solution = "Solution", userAnswewr = "Yourt answer";
		table.addContainerProperty("Question difficulty", Double.class, null);
		table.addContainerProperty("Result", String.class, null);
		table.addContainerProperty(userAnswewr, Button.class, null);
		table.addContainerProperty(solution, Button.class, null);
		List<HistoryEntry> entries = Lists.reverse(args.history);

		for (HistoryEntry entry : entries) {
			Button qAnswer = null, qSolution = null;
			if (entry.question instanceof Component && entry.question != null) {
				try {
					Class<? extends AnswerStorage> dataStorageClass = entry.question
							.getSolution().getClass();
					Constructor<? extends IQuestion> constructor = entry.question
							.getClass()
							.getConstructor(dataStorageClass, dataStorageClass,
									Float.class, String.class);
					// The following casts can not fail, because the question is
					// a component as well
					Component iQuestionSolution = (Component) constructor
							.newInstance(entry.question.getSolution(),
									entry.question.getSolution(),
									entry.question.getDifficulty(),
									entry.question.getQuestionText());
					Component iQuestionUser = (Component) constructor
							.newInstance(entry.question.getSolution(),
									entry.question.getUserAnswer(),
									entry.question.getDifficulty(),
									entry.question.getQuestionText());
					// Create the 2 needed click listeners
					ClickListener clickListenerSol = event -> {
						Window window = new Window(solution);
						event.getButton().setEnabled(false);
						window.addCloseListener(e -> event.getButton()
								.setEnabled(true));
						window.setContent(iQuestionSolution);
						window.center();
						window.setWidth("90%");
						window.setHeight("50%");
						if (iQuestionSolution instanceof Sizeable) {
							Sizeable sizeable = iQuestionSolution;
							sizeable.setSizeFull();
						}
						getUI().addWindow(window);
					};

					ClickListener clickListenerUA = event -> {
						Window window = new Window(userAnswewr);
						event.getButton().setEnabled(false);
						window.addCloseListener(e -> event.getButton()
								.setEnabled(true));
						window.setContent(iQuestionUser);
						window.center();
						window.setWidth("90%");
						window.setHeight("50%");
						if (iQuestionUser instanceof Sizeable) {
							Sizeable sizeable = iQuestionUser;
							sizeable.setSizeFull();
						}
						getUI().addWindow(window);
					};

					// Create the two buttons
					qAnswer = new Button(userAnswewr, clickListenerUA);
					qSolution = new Button(solution, clickListenerSol);

				} catch (Exception e) {
					// Ignore this line
				}
			}

			table.addItem(
					new Object[] {
							entry.difficulty,
							isCorrect(entry.points,
									entry.question.getMaxPoints()), qAnswer,
							qSolution }, null);
		}
		int size = table.size();
		if (size > 10) {
			size = 10;
		}
		table.setPageLength(size);
		addComponent(table);
		setComponentAlignment(table, Alignment.MIDDLE_CENTER);

		addComponent(HtmlLabel.getCenteredLabel("h3",
				"Your skill level is: <b>" + args.skillLevel + "</b>"));
	}

	private String isCorrect(double points, double maxPoints) {
		return points + " / " + maxPoints + " (" + 100 * points / maxPoints
				+ "% )";
	}

	@Override
	public void enter(ViewChangeEvent event) {
		VaadinUI.setCurrentPageTitle(event);
	}

}

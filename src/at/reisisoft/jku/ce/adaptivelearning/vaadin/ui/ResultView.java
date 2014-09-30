package at.reisisoft.jku.ce.adaptivelearning.vaadin.ui;

import java.util.List;

import at.reisisoft.jku.ce.adaptivelearning.VaadinUI;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.HistoryEntry;
import at.reisisoft.jku.ce.adaptivelearning.core.engine.ResultFiredArgs;
import at.reisisoft.jku.ce.adaptivelearning.html.HtmlLabel;

import com.google.gwt.thirdparty.guava.common.collect.Lists;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class ResultView extends VerticalLayout implements View {

	private static final long serialVersionUID = -6619938011293967055L;

	public ResultView(ResultFiredArgs args, Label title) {
		setSpacing(true);
		addComponent(title);
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
		table.addContainerProperty("Question difficulty", Double.class, null);
		table.addContainerProperty("Result:", String.class, null);
		List<HistoryEntry> entries = Lists.reverse(args.history);
		for (HistoryEntry entry : entries) {
			table.addItem(new Object[] { entry.difficulty,
					isCorrect(entry.isCorrect) }, null);
		}
		int size = table.size();
		if (size > 10) {
			size = 10;
		}
		table.setPageLength(size);
		addComponent(table);
		setComponentAlignment(table, Alignment.MIDDLE_CENTER);

		addComponent(HtmlLabel.getCenteredLabel("h3", "Your skill level is: "
				+ args.skillLevel));
	}

	private String isCorrect(boolean isCorrect) {
		return isCorrect ? "Correct!" : "Incorrect!";
	}

	@Override
	public void enter(ViewChangeEvent event) {
		VaadinUI.setCurrentPageTitle(event);
	}

}

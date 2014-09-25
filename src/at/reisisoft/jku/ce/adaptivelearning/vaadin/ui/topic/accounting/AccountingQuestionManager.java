package at.reisisoft.jku.ce.adaptivelearning.vaadin.ui.topic.accounting;

import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountingDataProvider;
import at.reisisoft.jku.ce.adaptivelearning.vaadin.ui.QuestionManager;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class AccountingQuestionManager extends QuestionManager {

	public AccountingQuestionManager(String quizName, UI ui) {
		super(quizName);
		Button openKontenplan = new Button("Open Kontenplan");
		openKontenplan.addClickListener(e -> {
			openKontenplan.setEnabled(false);
			// Create Window with layout
			Window window = new Window("Kontenplan");
			GridLayout layout = new GridLayout(1, 1);
			layout.addComponent(AccountingDataProvider.getInstance()
					.toHtmlTable());
			layout.setSizeFull();
			window.setContent(layout);
			window.center();
			window.setWidth("60%");
			window.setHeight("80%");
			window.setResizable(false);
			window.addCloseListener(e1 -> openKontenplan.setEnabled(true));
			ui.addWindow(window);

		});
		addHelpButton(openKontenplan);

	}

	private static final long serialVersionUID = -4764723794449575244L;

}

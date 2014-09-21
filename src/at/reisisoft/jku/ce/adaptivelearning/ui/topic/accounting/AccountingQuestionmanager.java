package at.reisisoft.jku.ce.adaptivelearning.ui.topic.accounting;

import at.reisisoft.jku.ce.adaptivelearning.core.AnswerStorage;
import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;
import at.reisisoft.jku.ce.adaptivelearning.ui.QuestionManager;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class AccountingQuestionManager<Question extends Component & IQuestion<? extends AnswerStorage>>
extends QuestionManager<Question> {

	public AccountingQuestionManager(String quizName, UI ui) {
		super(quizName);
		Button openKontenplan = new Button("Open Kontenplan");
		openKontenplan
				.addClickListener(e -> {
					ThemeResource resource = new ThemeResource(
							"images/kontenplan.png");
					Image image = new Image("Kontenplan", resource);
					// Create Window with layout
				Window window = new Window("Kontenplan");
				GridLayout layout = new GridLayout(1, 1);
				layout.addComponent(image);
				layout.setSizeFull();
			window.setContent(layout);
			window.center();
			ui.addWindow(window);

			});
		addComponent(openKontenplan, Constraint.SOUTH);
	}

	private static final long serialVersionUID = -4764723794449575244L;

}

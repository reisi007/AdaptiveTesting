package at.reisisoft.jku.ce.adaptivelearning.ui;

import org.vaadin.addon.borderlayout.BorderLayout;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class QuestionManager extends BorderLayout {

	private static final long serialVersionUID = -1445092564755306295L;
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
}

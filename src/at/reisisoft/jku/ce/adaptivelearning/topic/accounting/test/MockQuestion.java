package at.reisisoft.jku.ce.adaptivelearning.topic.accounting.test;

import at.reisisoft.jku.ce.adaptivelearning.core.IAnswerStorage;
import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;
import at.reisisoft.jku.ce.adaptivelearning.ui.ExtBorderLayout;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class MockQuestion<Question extends IQuestion<T> & Component, T extends IAnswerStorage>
		extends ExtBorderLayout implements IQuestion<T> {

	private static final long serialVersionUID = 8387557147527424813L;
	private final Question question;

	public MockQuestion(Question question, UI ui) {
		this.question = question;
		addComponent(question, Constraint.CENTER);
		// Download the result
		Button button = new Button("Display current user's solution");
		addComponent(button, Constraint.SOUTH);
		TextArea textArea = new TextArea("Question text");
		textArea.setSizeFull();
		addComponent(textArea, Constraint.NORTH);
		button.setSizeFull();
		button.addClickListener(e -> {
			Window window = new Window("Current user solution");
			VerticalLayout layout = new VerticalLayout();
			layout.setMargin(true);
			window.setContent(layout);
			String questionText = textArea.getValue();
			Label label;

			questionText = questionText.replace("\n", "<br>");

			try {
				label = new Label(questionText + "<p>"
						+ question.getUserAnswer().toString(), ContentMode.HTML);
			} catch (Exception e1) {
				label = new Label("<h1>Error parsing XML</h1><p>"
						+ e1.getMessage());
			}
			/*
			 * question.setQuestionText(questionText);
			 * question.setSolution(question.getUserAnswer()); try { label = new
			 * Label(question.toXML(), ContentMode.HTML); } catch (Exception e1)
			 * { label = new Label("<h1>Error parsing XML</h1><p>" +
			 * e1.getMessage(), ContentMode.HTML);
			 * e1.printStackTrace(System.err); }
			 */
			layout.addComponent(label);
			window.center();
			ui.addWindow(window);
		});

	}

	@Override
	public T getSolution() {
		return question.getSolution();
	}

	@Override
	public T getUserAnswer() {
		return question.getUserAnswer();
	}

	@Override
	public boolean checkUserAnswer() {
		return question.checkUserAnswer();
	}

	@Override
	public float getDifficulty() {
		return question.getDifficulty();
	}
}

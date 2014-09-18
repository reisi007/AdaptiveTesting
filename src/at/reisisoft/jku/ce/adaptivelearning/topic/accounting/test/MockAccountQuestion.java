package at.reisisoft.jku.ce.adaptivelearning.topic.accounting.test;

import org.vaadin.addon.borderlayout.BorderLayout;

import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountRecordData;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountingDataStorage;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountingQuestion;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MockAccountQuestion extends BorderLayout {

	private static final long serialVersionUID = 8387557147527424813L;
	private final AccountingQuestion question;

	public MockAccountQuestion(int soll, int haben, UI ui) {
		AccountingDataStorage solution = new AccountingDataStorage();
		AccountRecordData[] accountRecordDatas = new AccountRecordData[soll];
		for (int i = 0; i < accountRecordDatas.length; i++) {
			accountRecordDatas[i] = new AccountRecordData();
		}
		solution.setSoll(accountRecordDatas);
		accountRecordDatas = new AccountRecordData[haben];
		for (int i = 0; i < accountRecordDatas.length; i++) {
			accountRecordDatas[i] = new AccountRecordData();
		}
		solution.setHaben(accountRecordDatas);
		question = new AccountingQuestion(solution, 0);
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
			questionText = questionText.replace("\n", "<br>");
			Label label = new Label(questionText + "<p>"
					+ question.getUserAnswer().toString(), ContentMode.HTML);
			layout.addComponent(label);
			window.center();
			ui.addWindow(window);
		});
	}
}

package at.reisisoft.jku.ce.adaptivelearning;

import javax.servlet.annotation.WebServlet;

import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.test.AccountingMockQuestion;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.test.ProfitMockquestion;
import at.reisisoft.jku.ce.adaptivelearning.ui.MainUI;
import at.reisisoft.jku.ce.adaptivelearning.ui.QuestionManager;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("vaadin")
public class VaadinUI extends UI {
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VaadinUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		// Main UI layout
		MainUI layout = new MainUI(this);
		layout.setMargin(true);
		setContent(layout);

		// Uncomment next line: Mock Accounting Question
		// accountingLayoutTest(layout);

		// Uncomment next line: Mock Profit Question
		// profitLayouttest(layout);

		// Uncomment next line: Question manager with single question
		questionManagerTest(layout);
	}

	private void accountingLayoutTest(MainUI mainUI) {
		mainUI.addComponent(new AccountingMockQuestion(2, 2, this));
	}

	private void profitLayouttest(MainUI mainUI) {
		mainUI.addComponent(new ProfitMockquestion(this));
	}

	private void questionManagerTest(MainUI mainUI) {

		QuestionManager<?> manager = new QuestionManager("Test test");
		mainUI.addComponent(manager);
		AccountingMockQuestion question = new AccountingMockQuestion(3, 2, this);
		manager.addQuestion(question);
	}
}
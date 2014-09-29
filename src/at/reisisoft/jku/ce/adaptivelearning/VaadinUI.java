package at.reisisoft.jku.ce.adaptivelearning;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountingQuestion;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.test.AccountingMockQuestion;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.test.ProfitMockQuestion;
import at.reisisoft.jku.ce.adaptivelearning.vaadin.ui.MainUI;
import at.reisisoft.jku.ce.adaptivelearning.vaadin.ui.QuestionManager;
import at.reisisoft.jku.ce.adaptivelearning.vaadin.ui.topic.accounting.AccountingQuestionManager;

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
		@Override
		protected void servletInitialized() throws ServletException {
			super.servletInitialized();
			// Get the question folder as defined in WEB-INF/web.xml
			questionFolderName = getServletConfig().getServletContext()
					.getInitParameter(initKey);
			File file = new File(questionFolderName);
			boolean isWorking = file.exists() && file.isDirectory()
					|| file.mkdirs();
			if (!isWorking) {
				questionFolderName = null;
			}
		}

		private static String questionFolderName = null;
		private final static String initKey = "at.reisisoft.jku.ce.adaptivelearning.questionfolder";

		/**
		 * Gets the question folder name
		 *
		 * @return NULL if not set, or the String is not a valid folder / a
		 *         folder at this location could not be created.
		 */
		public static String getQuestionFolderName() {
			return questionFolderName;
		}
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
		questionManagerTest1(layout);

	}

	private void accountingLayoutTest(MainUI mainUI) {
		mainUI.addComponent(new AccountingMockQuestion(2, 2, this));
	}

	private void profitLayouttest(MainUI mainUI) {
		mainUI.addComponent(new ProfitMockQuestion(this));
	}

	private void questionManagerTest1(MainUI mainUI) {

		QuestionManager manager = new AccountingQuestionManager("Test test",
				this);
		mainUI.addComponent(manager);
		AccountingQuestion question = new AccountingQuestion();
		question.setQuestionText("123 Test");
		manager.addQuestion(question);
		manager.startQuiz();
	}

	private void questionManagerTest2(MainUI mainUI) {

		QuestionManager manager = new AccountingQuestionManager("Test test",
				this);
		mainUI.addComponent(manager);
		ProfitMockQuestion question = new ProfitMockQuestion(this);
		manager.addQuestion(question);
		manager.startQuiz();
	}
}
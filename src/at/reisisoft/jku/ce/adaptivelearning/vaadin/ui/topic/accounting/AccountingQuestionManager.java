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

		// StringBuilder sb = new StringBuilder();
		// ScriptEngine engine;
		// try {
		// engine = RProvider.getREngine();
		//
		// String command = "data.frame(x=1:10, y=(1:10)+rnorm(n=10))";
		// ListVector vector = (ListVector) engine.eval(command);
		// IntSequence sequence = (IntSequence) vector.get(0);
		// sb.append("Welcome R!\nCan you please compute the following:<br><b><i>\""
		// + command + "\"</i></b><br>");
		// DoubleArrayVector doubleArrayVector = (DoubleArrayVector) vector
		// .get(1);
		// for (int i = 0; i < sequence.length(); i++) {
		// sb.append("x=" + sequence.getElementAsInt(i) + "    y="
		// + doubleArrayVector.get(i) + "<br>");
		// }
		// } catch (ScriptException e) {
		// sb.append(e.getMessage());
		// }
		// ui.addWindow(new Window("R Test", new HorizontalLayout(new Label(sb
		// .toString(), ContentMode.HTML))));

	}

	private static final long serialVersionUID = -4764723794449575244L;

}

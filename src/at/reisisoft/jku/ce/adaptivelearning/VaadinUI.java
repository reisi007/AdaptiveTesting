package at.reisisoft.jku.ce.adaptivelearning;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addon.borderlayout.BorderLayout.Constraint;

import at.reisisoft.jku.ce.adaptivelearning.ui.MainUI;
import at.reisisoft.jku.ce.adaptivelearning.ui.topic.accounting.AccountingRecordInputFields;

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
		layout.addComponent(new AccountingRecordInputFields(),
				Constraint.CENTER);
		// Set start screen
	}

}
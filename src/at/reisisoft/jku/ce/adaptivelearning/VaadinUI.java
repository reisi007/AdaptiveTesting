package at.reisisoft.jku.ce.adaptivelearning;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addon.borderlayout.BorderLayout.Constraint;

import at.reisisoft.jku.ce.adaptivelearning.ui.MainUI;
import at.reisisoft.jku.ce.adaptivelearning.vaadin.currencyinput.CurrencyTextBox;
import at.reisisoft.jku.ce.adaptivelearning.vaadin.currencyinput.ValidValueChangedListener;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("vaadin")
public class VaadinUI extends UI {
	Label l = new Label();
	CurrencyTextBox tf = new CurrencyTextBox();

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VaadinUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		MainUI layout = new MainUI(this);
		GridLayout centerLayout = new GridLayout(1, 2);

		centerLayout.addComponent(tf);
		centerLayout.addComponent(l);
		layout.setMargin(true);

		ValidValueChangedListener<Float> listener = new ValidValueChangedListener<Float>() {

			@Override
			public void accept(Float changedValue) {
				l.setValue("New value is " + changedValue.floatValue() + " €");

			}
		};
		tf.addListener(listener);
		setContent(layout);
		layout.addComponent(centerLayout, Constraint.CENTER);

	}

}
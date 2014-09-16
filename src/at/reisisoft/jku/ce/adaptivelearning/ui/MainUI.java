package at.reisisoft.jku.ce.adaptivelearning.ui;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.vaadin.addon.borderlayout.BorderLayout;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class MainUI extends BorderLayout {

	private static final long serialVersionUID = 4966805861748123750L;

	public MainUI(final UI ui) {
		setSizeFull();
		GridLayout southLayout = new GridLayout(3, 1);
		HorizontalLayout flowLayout = new HorizontalLayout();
		flowLayout.setSpacing(true);
		addComponent(southLayout, Constraint.SOUTH);
		southLayout.addComponent(flowLayout, 1, 0);
		final Button licences = new Button("Show 3rd party licences");
		licences.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 32642854872179636L;

			@Override
			public void buttonClick(ClickEvent event) {
				Window licenceWindow = new Window("Licences");
				VerticalLayout vLayout = new VerticalLayout();
				vLayout.setMargin(true);
				licenceWindow.setContent(vLayout);
				// Add the 3rd party licences as Labels
				vLayout.addComponent(new Label(
						"<a href='http://vaadin.com/addon/borderlayout'target='_blank'>Borderlayout 0.5</a> (<a href='http://www.apache.org/licenses/LICENSE-2.0.html' target='_blank'>Apache License 2.0</a>) ",
						ContentMode.HTML));
				licenceWindow.center();
				ui.addWindow(licenceWindow);
				licences.setEnabled(false);
				licenceWindow.addCloseListener(new CloseListener() {

					private static final long serialVersionUID = 7874342882437355680L;

					@Override
					public void windowClose(CloseEvent e) {
						licences.setEnabled(true);

					}
				});
			}

		});
		flowLayout.addComponent(licences);
		flowLayout.addComponent(new Label("© Reisisoft 2014 - "
				+ new GregorianCalendar().get(Calendar.YEAR)));
	}
}

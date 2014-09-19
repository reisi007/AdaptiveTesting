package at.reisisoft.jku.ce.adaptivelearning.ui;

import org.vaadin.addon.borderlayout.BorderLayout;

import com.vaadin.ui.Component;

public class ExtBorderLayout extends BorderLayout {

	private static final long serialVersionUID = -5799876889295641955L;

	@Override
	public void addComponent(Component c) {
		addComponent(c, Constraint.CENTER);
	}

}

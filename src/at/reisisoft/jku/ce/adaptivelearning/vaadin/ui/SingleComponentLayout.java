package at.reisisoft.jku.ce.adaptivelearning.vaadin.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

public class SingleComponentLayout extends HorizontalLayout {

	private static final long serialVersionUID = -5260460847590307512L;

	public SingleComponentLayout() {
		setSizeFull();
	}

	@Override
	public void addComponent(Component c, int index) {
		removeAllComponents();
		super.addComponent(c, index);
	}

}

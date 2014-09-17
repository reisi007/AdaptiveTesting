package at.reisisoft.jku.ce.adaptivelearning.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;

public class SingleComponentLayout extends GridLayout {

	public SingleComponentLayout() {
		super(1, 1);
		setSizeFull();
	}

	private static final long serialVersionUID = 5153298889662882239L;

	@Override
	public void addComponent(Component component, int column1, int row1,
			int column2, int row2) throws OverlapsException,
			OutOfBoundsException {
		internalAddComponent(component);
	}

	@Override
	public void addComponent(Component component, int column, int row)
			throws OverlapsException, OutOfBoundsException {
		internalAddComponent(component);
	}

	@Override
	public void addComponent(Component component) {
		internalAddComponent(component);
	}

	private void internalAddComponent(Component component) {
		removeAllComponents();
		super.addComponent(component);
	}

}

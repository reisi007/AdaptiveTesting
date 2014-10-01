package at.reisisoft.jku.ce.adaptivelearning.vaadin.ui.topic.accounting;

import at.reisisoft.jku.ce.adaptivelearning.html.HtmlLabel;
import at.reisisoft.jku.ce.adaptivelearning.vaadin.ui.SingleComponentLayout;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public abstract class AccountingRecordInputGrid extends SingleComponentLayout {
	public enum Side {
		Right, Left
	};

	private static final long serialVersionUID = 3423260539583285740L;
	// Create all necessary layouts
	private VerticalLayout outer = new VerticalLayout();
	private GridLayout inner = new GridLayout(2, 1);
	private GridLayout right = new GridLayout(1, 4);
	private GridLayout left = new GridLayout(1, 4);

	public AccountingRecordInputGrid() {
		// Make the layout
		addComponent(outer);
		outer.addComponent(inner);
		inner.addComponent(left, 0, 0);
		inner.addComponent(right, 1, 0);
		// Make layout size full
		inner.setSizeFull();
		right.setSizeFull();
		left.setSizeFull();
		left.addComponent(new HtmlLabel("<b>Soll</b>"), 0, 0);
		right.addComponent(new HtmlLabel("<b>Haben</b>"), 0, 0);
	}

	public void addComponent(Component component, Side side, int position) {
		assert side != null;
		GridLayout current = side == Side.Right ? right : left;
		current.addComponent(component, 0, position + 1);
	}

	protected void addQuestionText(String text) {
		outer.addComponent(new Label(text, ContentMode.HTML), 0);
	}

}

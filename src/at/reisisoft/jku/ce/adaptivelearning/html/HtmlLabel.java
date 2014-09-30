package at.reisisoft.jku.ce.adaptivelearning.html;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class HtmlLabel extends Label {

	public HtmlLabel(String content) {
		super(content, ContentMode.HTML);
	}

	public static HtmlLabel getCenteredLabel(String text) {
		return getCenteredLabel("div", text);
	}

	public static HtmlLabel getCenteredLabel(String tag, String text) {
		return new HtmlLabel(HtmlUtils.center(tag, text));
	}

	private static final long serialVersionUID = 3371602359798072688L;

}

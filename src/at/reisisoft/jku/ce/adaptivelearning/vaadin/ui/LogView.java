package at.reisisoft.jku.ce.adaptivelearning.vaadin.ui;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import at.reisisoft.jku.ce.adaptivelearning.core.LogHelper;

import com.google.gwt.thirdparty.guava.common.io.Files;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class LogView extends Label implements View {
	private File file;

	public LogView(File file) {
		this.file = file;
		setContentMode(ContentMode.PREFORMATTED);

	}

	private static final long serialVersionUID = 5066697583930352899L;

	@Override
	public void enter(ViewChangeEvent event) {
		String fileContent;
		try {
			fileContent = Files.toString(file, Charset.defaultCharset());
		} catch (IOException e) {
			LogHelper.logThrowable(e);
			fileContent = "Could not load file due to:"
					+ e.getClass().getName() + " " + e.getMessage();
		}
		setValue(fileContent);
	}
}
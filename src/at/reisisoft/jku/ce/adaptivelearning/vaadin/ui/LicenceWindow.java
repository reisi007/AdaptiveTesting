package at.reisisoft.jku.ce.adaptivelearning.vaadin.ui;

import at.reisisoft.jku.ce.adaptivelearning.html.HtmlLink;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class LicenceWindow extends Window {

	private static final long serialVersionUID = -7005783695341501851L;
	private VerticalLayout vLayout = new VerticalLayout();
	private final HtmlLink apache2 = new HtmlLink(
			"http://www.apache.org/licenses/LICENSE-2.0.html",
			"Apache 2.0 Lizenz", true);
	private final HtmlLink lgpl3 = new HtmlLink(
			"http://www.gnu.org/licenses/lgpl.html", "LGPL v3", true);

	public LicenceWindow() {
		super("Licences");
		center();
		// Layout for the window
		setContent(vLayout);
		vLayout.setMargin(true);
		// Add the 3rd party licences
		addAddonLicence(new HtmlLink("http://vaadin.com/addon/borderlayout",
				"BorderLayout", true), apache2);
		addAddonLicence(new HtmlLink("http://www.mhsatman.com/rcaller.php",
				"RCaller 2.0", true), lgpl3);
	}

	public void addAddonLicence(HtmlLink addon, HtmlLink licence) {
		vLayout.addComponent(new Label(addon + " ( " + licence + ')',
				ContentMode.HTML));
	}

}

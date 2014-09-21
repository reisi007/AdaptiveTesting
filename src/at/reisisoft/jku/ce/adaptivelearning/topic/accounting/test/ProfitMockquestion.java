package at.reisisoft.jku.ce.adaptivelearning.topic.accounting.test;

import at.reisisoft.jku.ce.adaptivelearning.core.MockQuestion;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.ProfitDataStorage;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.ProfitQuestion;

import com.vaadin.ui.UI;

public class ProfitMockquestion extends
		MockQuestion<ProfitQuestion, ProfitDataStorage> {

	public ProfitMockquestion(UI ui) {
		super(new ProfitQuestion(new ProfitDataStorage(null), 0f, ""), ui);
	}

	private static final long serialVersionUID = -4498805944562368205L;

}

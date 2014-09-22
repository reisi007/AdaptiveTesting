package at.reisisoft.jku.ce.adaptivelearning.xml.topic.accounting;

import javax.xml.bind.annotation.XmlRootElement;

import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.ProfitDataStorage;
import at.reisisoft.jku.ce.adaptivelearning.xml.XmlQuestionData;

@XmlRootElement(name = "profitQuestionDataStorage")
public class XmlProfitQuestion extends XmlQuestionData<ProfitDataStorage> {

	private static final long serialVersionUID = -7011780466232380923L;

	public XmlProfitQuestion() {

	}

	public XmlProfitQuestion(ProfitDataStorage solution, String questionText,
			float difficulty) {
		super(solution, questionText, difficulty);
	}

	@Override
	public Class<ProfitDataStorage> getDataStorageClass() {
		return ProfitDataStorage.class;
	}

}

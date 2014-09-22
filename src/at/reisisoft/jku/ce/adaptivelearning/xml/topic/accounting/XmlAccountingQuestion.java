package at.reisisoft.jku.ce.adaptivelearning.xml.topic.accounting;

import javax.xml.bind.annotation.XmlRootElement;

import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountingDataStorage;
import at.reisisoft.jku.ce.adaptivelearning.xml.XmlQuestionData;

@XmlRootElement(name = "accountingtQuestionDataStorage")
public class XmlAccountingQuestion extends
XmlQuestionData<AccountingDataStorage> {

	private static final long serialVersionUID = 3262285204313858210L;

	public XmlAccountingQuestion() {
	}

	public XmlAccountingQuestion(AccountingDataStorage solution,
			String questionText, float difficulty) {
		super(solution, questionText, difficulty);
	}

	@Override
	public Class<AccountingDataStorage> getDataStorageClass() {
		return AccountingDataStorage.class;
	}

}

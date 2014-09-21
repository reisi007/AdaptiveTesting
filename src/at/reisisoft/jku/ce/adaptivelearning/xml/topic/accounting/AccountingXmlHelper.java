package at.reisisoft.jku.ce.adaptivelearning.xml.topic.accounting;

import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountingDataStorage;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountingQuestion;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.ProfitDataStorage;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.ProfitQuestion;
import at.reisisoft.jku.ce.adaptivelearning.xml.XmlQuestionData;

public final class AccountingXmlHelper {
	public AccountingQuestion AccountingQuestionfromXML(
			XmlQuestionData<AccountingDataStorage> xml) {
		return new AccountingQuestion(xml.getDataStorage(),
				xml.getDifficulty(), xml.getQuestion());
	}

	public ProfitQuestion ProfitQuestionfromXML(
			XmlQuestionData<ProfitDataStorage> xml) {
		return new ProfitQuestion(xml.getDataStorage(), xml.getDifficulty(),
				xml.getQuestion());

	}
}

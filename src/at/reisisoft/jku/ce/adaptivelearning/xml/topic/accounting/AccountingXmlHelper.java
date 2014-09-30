package at.reisisoft.jku.ce.adaptivelearning.xml.topic.accounting;

import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountingQuestion;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.ProfitQuestion;

public final class AccountingXmlHelper {
	public static AccountingQuestion fromXml(XmlAccountingQuestion xml) {
		return new AccountingQuestion(xml.getDataStorage(),
				xml.getDifficulty(), xml.getQuestion());
	}

	public static ProfitQuestion fromXml(XmlProfitQuestion xml) {
		return new ProfitQuestion(xml.getDataStorage(), xml.getDifficulty(),
				xml.getQuestion());

	}
}

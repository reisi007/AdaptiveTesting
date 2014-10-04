package at.reisisoft.jku.ce.adaptivelearning.topic.accounting.test;

import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountRecordData;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountingDataStorage;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountingQuestion;
import at.reisisoft.jku.ce.adaptivelearning.vaadin.ui.MockQuestion;

public class AccountingMockQuestion extends
		MockQuestion<AccountingQuestion, AccountingDataStorage> {

	public AccountingMockQuestion(AccountingDataStorage solution,
			AccountingDataStorage dataStorage, float difficulty,
			String questionText) {
		super(new AccountingQuestion(solution, dataStorage, difficulty, ""));
	}

	public AccountingMockQuestion(int soll, int haben) {
		super(new AccountingQuestion(getData(soll, haben), 0f, ""));
	}

	private static AccountingDataStorage getData(int soll, int haben) {
		AccountingDataStorage data = new AccountingDataStorage();
		AccountRecordData[] accountRecordDatas = new AccountRecordData[soll];
		for (int i = 0; i < accountRecordDatas.length; i++) {
			accountRecordDatas[i] = new AccountRecordData();
		}
		data.setSoll(accountRecordDatas);
		accountRecordDatas = new AccountRecordData[haben];
		for (int i = 0; i < accountRecordDatas.length; i++) {
			accountRecordDatas[i] = new AccountRecordData();
		}
		data.setHaben(accountRecordDatas);
		return data;
	}

	private static final long serialVersionUID = -1864771968892486555L;

}

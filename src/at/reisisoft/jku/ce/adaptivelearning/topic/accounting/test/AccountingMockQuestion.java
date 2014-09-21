package at.reisisoft.jku.ce.adaptivelearning.topic.accounting.test;

import at.reisisoft.jku.ce.adaptivelearning.core.MockQuestion;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountRecordData;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountingDataStorage;
import at.reisisoft.jku.ce.adaptivelearning.topic.accounting.AccountingQuestion;

import com.vaadin.ui.UI;

public class AccountingMockQuestion extends
		MockQuestion<AccountingQuestion, AccountingDataStorage> {

	public AccountingMockQuestion(int soll, int haben, UI ui) {
		super(new AccountingQuestion(getData(soll, haben), 0,
				"No question text yet"), ui);
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

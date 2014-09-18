package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

import at.reisisoft.jku.ce.adaptivelearning.core.IAnswerStorage;

public class AccountingDataStorage implements IAnswerStorage {

	private AccountRecordData[] soll, haben;

	public AccountingDataStorage() {
	}

	public AccountRecordData[] getSoll() {
		return soll;
	}

	public AccountRecordData[] getHaben() {
		return haben;
	}

	public void setSoll(AccountRecordData[] data) {
		if (data != null) {
			soll = data;
		}
	}

	public void setHaben(AccountRecordData[] data) {
		if (data != null) {
			haben = data;
		}
	}
}

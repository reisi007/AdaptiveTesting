package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

import java.util.ArrayList;
import java.util.List;

import at.reisisoft.jku.ce.adaptivelearning.core.IAnswerStorage;

public class AccountingDataStorage implements IAnswerStorage {

	public AccountingDataStorage() {
		soll = new ArrayList<>();
		haben = new ArrayList<>();
	}

	public final List<AccountRecordData> soll, haben;

	public void addSoll(AccountRecordData data) {
		soll.add(data);
	}

	public void addHaben(AccountRecordData data) {
		haben.add(data);
	}
}
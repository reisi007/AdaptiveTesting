package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

import java.io.Serializable;

import at.reisisoft.jku.ce.adaptivelearning.core.IAnswerStorage;

public class AccountingDataStorage implements IAnswerStorage, Serializable {
	private static final long serialVersionUID = -8179746363246548456L;
	private AccountRecordData[] soll = new AccountRecordData[0];
	private AccountRecordData[] haben = new AccountRecordData[0];

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

	@Override
	public String toString() {
		return toString(true);
	}

	public String toString(boolean html) {
		String nl = html ? "<br>" : System.getProperty("line.separator");
		int max = soll.length > haben.length ? soll.length : haben.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < max; i++) {
			if (i < soll.length) {
				sb.append(soll[i]);
			}
			sb.append("  |  ");
			if (i < haben.length) {
				sb.append(haben[i]);
			}
			sb.append(nl);
		}
		return sb.toString();
	}
}

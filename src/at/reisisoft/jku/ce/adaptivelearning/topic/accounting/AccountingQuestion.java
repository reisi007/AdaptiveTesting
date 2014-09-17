package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;
import at.reisisoft.jku.ce.adaptivelearning.ui.topic.accounting.AccountingRecordInputGrid;

public class AccountingQuestion extends AccountingRecordInputGrid implements
IQuestion<AccountingDataStorage> {

	@Override
	public AccountingDataStorage getSolution() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountingDataStorage getUserAnswer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkUserAnswer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getDifficulty() {
		// TODO Auto-generated method stub
		return 0;
	}

}

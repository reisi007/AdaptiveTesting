package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

import java.util.Arrays;
import java.util.List;

import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;
import at.reisisoft.jku.ce.adaptivelearning.ui.topic.accounting.AccountingRecordInputFields;
import at.reisisoft.jku.ce.adaptivelearning.ui.topic.accounting.AccountingRecordInputGrid;

public class AccountingQuestion extends AccountingRecordInputGrid implements
		IQuestion<AccountingDataStorage> {
	private static final long serialVersionUID = 5932474069705038565L;
	private float difficulty;
	private AccountingDataStorage solution;
	private AccountingRecordInputFields[] soll, haben;

	public AccountingQuestion(AccountingDataStorage solution, float difficulty) {
		this.difficulty = difficulty;
		this.solution = solution;
		// Fill grid
		int iSoll = solution.getSoll().length, iHaben = solution.getHaben().length;
		soll = new AccountingRecordInputFields[iSoll];
		haben = new AccountingRecordInputFields[iHaben];
		for (int row = 0; row < iSoll; row++) {
			soll[row] = new AccountingRecordInputFields();
			addComponent(soll[row], Side.Left, row);
		}
		for (int row = 0; row < iHaben; row++) {
			haben[row] = new AccountingRecordInputFields();
			addComponent(haben[row], Side.Right, row);
		}

	}

	@Override
	public AccountingDataStorage getSolution() {
		return solution;
	}

	@Override
	public AccountingDataStorage getUserAnswer() {
		AccountingDataStorage dataStorage = new AccountingDataStorage();
		AccountRecordData[] accountRecordDatas;
		// Add Soll
		accountRecordDatas = new AccountRecordData[soll.length];
		for (int i = 0; i < soll.length; i++) {
			accountRecordDatas[i] = soll[i].getAccountRecordData();
		}
		dataStorage.setSoll(accountRecordDatas);
		// Add haben
		accountRecordDatas = new AccountRecordData[haben.length];
		for (int i = 0; i < haben.length; i++) {
			accountRecordDatas[i] = haben[i].getAccountRecordData();
		}
		dataStorage.setHaben(accountRecordDatas);
		return dataStorage;
	}

	@Override
	public boolean checkUserAnswer() {
		AccountingDataStorage user = getUserAnswer(), solution = getSolution();
		AccountRecordData[] uSoll = user.getSoll(), uHaben = user.getHaben(), sSoll = solution
				.getSoll(), sHaben = solution.getHaben();
		// Return if the answer is right
		return uSoll.length == sSoll.length && uHaben.length == sHaben.length
				&& check(sSoll, uSoll) && check(sHaben, uHaben);

	}

	private boolean check(AccountRecordData[] solution, AccountRecordData[] user) {
		List<AccountRecordData> aUser = Arrays.asList(user);
		for (AccountRecordData data : solution) {
			if (aUser.indexOf(data) == -1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public float getDifficulty() {
		return difficulty;
	}

}

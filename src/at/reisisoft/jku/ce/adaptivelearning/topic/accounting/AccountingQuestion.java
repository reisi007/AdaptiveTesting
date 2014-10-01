package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

import java.util.Arrays;
import java.util.List;

import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;
import at.reisisoft.jku.ce.adaptivelearning.vaadin.ui.topic.accounting.AccountingRecordInputFields;
import at.reisisoft.jku.ce.adaptivelearning.vaadin.ui.topic.accounting.AccountingRecordInputGrid;
import at.reisisoft.jku.ce.adaptivelearning.xml.XmlQuestionData;
import at.reisisoft.jku.ce.adaptivelearning.xml.topic.accounting.XmlAccountingQuestion;

public class AccountingQuestion extends AccountingRecordInputGrid implements
IQuestion<AccountingDataStorage> {
	private static final long serialVersionUID = 5932474069705038565L;
	private String questionText;
	private float difficulty = 0;
	private AccountingDataStorage solution;
	private AccountingRecordInputFields[] soll, haben;

	public AccountingQuestion() {
		this(getEmptyDataStorage(), 0, "");
	}

	private static AccountingDataStorage getEmptyDataStorage() {
		AccountingDataStorage ds = new AccountingDataStorage();
		AccountRecordData[] accountRecordDatas = new AccountRecordData[3];
		for (int i = 0; i < accountRecordDatas.length; i++) {
			accountRecordDatas[i] = new AccountRecordData();
		}
		ds.setSoll(accountRecordDatas);
		accountRecordDatas = new AccountRecordData[3];
		for (int i = 0; i < accountRecordDatas.length; i++) {
			accountRecordDatas[i] = new AccountRecordData();
		}
		ds.setHaben(accountRecordDatas);
		return ds;
	}

	public AccountingQuestion(AccountingDataStorage solution, float difficulty,
			String question) {
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

	public void setSolution(AccountingDataStorage solution) {
		if (solution != null) {
			this.solution = solution;
		}
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
	public double checkUserAnswer() {
		AccountingDataStorage user = getUserAnswer(), solution = getSolution();
		AccountRecordData[] uSoll = user.getSoll(), uHaben = user.getHaben(), sSoll = solution
				.getSoll(), sHaben = solution.getHaben();
		// Return if the answer is right
		return uSoll.length == sSoll.length && uHaben.length == sHaben.length
				&& check(sSoll, uSoll) && check(sHaben, uHaben) ? 1d : 0d;

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

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
		addQuestionText(questionText);
	}

	@Override
	public XmlQuestionData<AccountingDataStorage> toXMLRepresentation() {
		return new XmlAccountingQuestion(getSolution(), getQuestionText(),
				getDifficulty());
	}

	@Override
	public double getMaxPoints() {
		return 1d;
	}

}

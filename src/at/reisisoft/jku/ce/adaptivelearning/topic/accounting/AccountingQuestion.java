package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

import java.util.List;

import at.reisisoft.jku.ce.adaptivelearning.core.IQuestion;
import at.reisisoft.jku.ce.adaptivelearning.ui.topic.accounting.AccountingRecordInputGrid;

public class AccountingQuestion extends AccountingRecordInputGrid implements
IQuestion<AccountingDataStorage> {
	private static final long serialVersionUID = 5932474069705038565L;
	private int difficulty;
	private AccountingDataStorage solution;

	public AccountingQuestion(AccountingDataStorage solution, int difficulty) {
		this.difficulty = difficulty;
		this.solution = solution;
		// TODO fill grid
	}

	@Override
	public AccountingDataStorage getSolution() {
		return solution;
	}

	@Override
	public AccountingDataStorage getUserAnswer() {
		// TODO Return a AccountDataStorage of the current state
		return null;
	}

	@Override
	public boolean checkUserAnswer() {
		AccountingDataStorage user = getUserAnswer(), solution = getSolution();
		List<AccountRecordData> uSoll = user.getSoll(), uHaben = user
				.getHaben(), sSoll = solution.getSoll(), sHaben = solution
				.getHaben();
		// Return if the answer is right
		return uSoll.size() == sSoll.size() && uHaben.size() == sHaben.size()
				&& check(sSoll, uSoll) && check(sHaben, uHaben);

	}

	private boolean check(List<AccountRecordData> solution,
			List<AccountRecordData> user) {
		for (AccountRecordData data : solution) {
			if (user.indexOf(data) == -1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int getDifficulty() {
		return difficulty;
	}

}

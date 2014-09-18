package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

public class AccountRecordData {
	@Override
	public String toString() {
		return "(" + accountNumber + ") "
				+ (accountName == null ? "???" : accountName) + " " + value
				+ '€';
	}

	public final String accountName;
	public final float value;
	public final int accountNumber;

	public AccountRecordData() {
		this("", 0, 0);
	}

	public AccountRecordData(String accountname, float value, int accountNumber) {
		accountName = accountname;
		this.value = value;
		this.accountNumber = accountNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result
				+ (accountName == null ? 0 : accountName.hashCode());
		result = prime * result + Float.floatToIntBits(value);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AccountRecordData other = (AccountRecordData) obj;
		if (accountNumber != other.accountNumber) {
			return false;
		}
		if (accountName == null) {
			if (other.accountName != null) {
				return false;
			}
		} else if (!accountName.equals(other.accountName)) {
			return false;
		}
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value)) {
			return false;
		}
		return true;
	}

}

package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

public class AccountRecordData {
	public final String accountname;
	public final float value;
	public final int accountNumber;

	public AccountRecordData(String accountname, float value, int accountNumber) {
		this.accountname = accountname;
		this.value = value;
		this.accountNumber = accountNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result
				+ (accountname == null ? 0 : accountname.hashCode());
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
		if (accountname == null) {
			if (other.accountname != null) {
				return false;
			}
		} else if (!accountname.equals(other.accountname)) {
			return false;
		}
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value)) {
			return false;
		}
		return true;
	}

}

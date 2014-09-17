package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

public class AccountRecordData {
	public final String kontoName;
	public final float value;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (kontoName == null ? 0 : kontoName.hashCode());
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
		if (kontoName == null) {
			if (other.kontoName != null) {
				return false;
			}
		} else if (!kontoName.equals(other.kontoName)) {
			return false;
		}
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value)) {
			return false;
		}
		return true;
	}

	public AccountRecordData(String kontoName, float value) {
		super();
		this.kontoName = kontoName;
		this.value = value;
	}
}

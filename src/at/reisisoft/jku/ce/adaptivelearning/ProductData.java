package at.reisisoft.jku.ce.adaptivelearning;

public class ProductData {
	private final int mjVers = 0;
	private final int minVers = 1;
	private final int micVers = 0;
	private final String company = "Reisisoft";
	private final String product = "Adaptive Testing";

	public int getMajor() {
		return mjVers;
	}

	public int getMinor() {
		return minVers;
	}

	public int getMicro() {
		return micVers;
	}

	public int getMjVers() {
		return mjVers;
	}

	public int getMinVers() {
		return minVers;
	}

	public int getMicVers() {
		return micVers;
	}

	public String getCompany() {
		return company;
	}

	public String getProduct() {
		return product;
	}

	@Override
	public String toString() {
		return getCompany() + ' ' + getProduct() + " v. " + getVersion();
	}

	public String getVersion() {
		return getMajor() + "." + getMinor() + '.' + getMicro();
	}

}

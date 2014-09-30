package at.reisisoft.jku.ce.adaptivelearning;

public enum Views {
	DEFAULT("Start"), TEST("Test"), RESULT("Result");
	private String string;

	private Views(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}

}

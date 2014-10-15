package at.reisisoft.jku.ce.adaptivelearning.core.engine;

public class EngineException extends Exception {

	private static final long serialVersionUID = 7510476603149718996L;

	public EngineException(String message) {
		super(message);
	}

	public EngineException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

}

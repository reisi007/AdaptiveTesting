package at.reisisoft.jku.ce.adaptivelearning.r;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RProvider {

	public static final ScriptEngine getREngine() throws ScriptException {
		ScriptEngine engine = new ScriptEngineManager()
		.getEngineByName("Renjin");
		if (engine == null) {
			throw new ScriptException("Renjin not found");
		}
		return engine;
	}

	// public static void main(String[] args) throws ScriptException {
	// // Test Renjin
	// ScriptEngine engine = RProvider.getREngine();
	// String command = "data.frame(x=1:10, y=(1:10)+rnorm(n=10))";
	// ListVector vector = (ListVector) engine.eval(command);
	// IntSequence sequence = (IntSequence) vector.get(0);
	// System.out
	// .println("Welcome R!\nCan you please compute the following:\n\""
	// + command + '"');
	// DoubleArrayVector doubleArrayVector = (DoubleArrayVector) vector.get(1);
	// for (int i = 0; i < sequence.length(); i++) {
	// System.out.println("x=" + sequence.getElementAsInt(i) + "\ty="
	// + doubleArrayVector.get(i));
	// }
	// }
}

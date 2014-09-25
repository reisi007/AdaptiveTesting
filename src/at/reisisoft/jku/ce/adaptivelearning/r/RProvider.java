package at.reisisoft.jku.ce.adaptivelearning.r;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Optional;

import javax.script.ScriptException;

import rcaller.Globals;
import rcaller.RCaller;
import rcaller.RCode;
import rcaller.ROutputParser;

public class RProvider {

	private RCaller caller = null;
	private RCode code = null;

	public RCaller getRCaller() throws ScriptException {
		if (caller == null) {
			// Guess R location
			Globals.detect_current_rscript();
			StringBuilder rPath = new StringBuilder(Globals.R_current);
			// Guessing is not good on Windows, DIY
			if (System.getProperty("os.name").startsWith("Win")) {
				rPath.setLength(0);
				rPath.append("C:\\Program Files\\R");
				File rFolder = new File(rPath.toString());

				File[] subfolders = rFolder
						.listFiles((FileFilter) pathname -> pathname
								.isDirectory());

				Optional<String> max = Arrays.asList(subfolders).stream()
						.map(e -> e.getName())
						.max((e1, e2) -> e1.compareTo(e2));

				if (!max.isPresent()) {
					throw new ScriptException("R is not present @ " + rPath);
				}
				rPath.append('\\').append(max.get()).append('\\')
						.append("bin\\");

				if (!"x86".equals(System.getProperty("os.arch"))) {
					// 64 bit VM -> 64 bit R required
					rPath.append("x64\\");
				}
				rPath.append("R.exe");
			}
			caller = new RCaller();
			try {
				caller.setRExecutable(rPath.toString());
			} catch (Exception e) {
				// Like: File not found
				throw new ScriptException(e);
			}
		}
		return caller;
	}

	public RCode getRCode() {
		if (code == null) {
			code = new RCode();
		}
		return code;
	}

	public ROutputParser execute(String toReturn) {
		return execute(caller, code, toReturn);
	}

	public ROutputParser execute(RCaller caller, RCode code, String toReturn) {
		caller.setRCode(code);
		caller.runAndReturnResultOnline(toReturn);
		code.clearOnline();
		return caller.getParser();
	}

	public void stopR() {
		caller.stopStreamConsumers();
		caller.StopRCallerOnline();
	}

	public static void main(String[] args) throws ScriptException {
		// Prerequirement
		RProvider rProvider = new RProvider();
		rProvider.getRCaller();
		RCode code = rProvider.getRCode();
		// Doing R magic
		code.addDoubleArray("x", new double[] { 1.0, 2.0, 3.0, 4.0, 50.0 });
		code.addRCode("result <- mean(x)");
		double mean = rProvider.execute("result").getAsDoubleArray("result")[0];
		System.out.println("mean: " + mean);
		// Postrequirement
		rProvider.stopR();
	}
}
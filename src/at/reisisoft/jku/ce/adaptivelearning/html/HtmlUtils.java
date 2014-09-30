package at.reisisoft.jku.ce.adaptivelearning.html;

public class HtmlUtils {
	public static String center(String tag, String text) {
		StringBuilder sb = new StringBuilder();
		sb.append('<').append(tag)
		.append(" style='text-align: center;'>");
		sb.append(text);
		sb.append("</").append(tag).append('>');
		return sb.toString();
	}

	public static String center(String text) {
		return center("div", text);
	}
}

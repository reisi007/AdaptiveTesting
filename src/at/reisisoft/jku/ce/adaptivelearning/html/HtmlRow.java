package at.reisisoft.jku.ce.adaptivelearning.html;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class HtmlRow {

	// Test main
	public static void main(String[] args) {
		List<HtmlRow> rows = new ArrayList<>();
		rows.add(new HtmlRow("1020", "Wien"));
		rows.add(new HtmlRow("4020", "Linz"));
		String s = rows.stream().collect(toHtmlTable());
		System.out.println(s);
	}

	private String[] columns;

	public HtmlRow(String... elements) {
		columns = elements;
	}

	public HtmlRow(Collection<String> elements) {
		this(elements.toArray(new String[elements.size()]));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("<tr>");
		for (String e : columns) {
			sb.append("<td>").append(e).append("</td>");
		}
		return sb.append("</tr>").toString();
	}

	public static <T extends HtmlRow> Collector<T, StringBuilder, String> toHtmlTable() {

		return new Collector<T, StringBuilder, String>() {

			@Override
			public Supplier<StringBuilder> supplier() {
				return () -> new StringBuilder("<table>");
			}

			@Override
			public BiConsumer<StringBuilder, T> accumulator() {
				return (t, u) -> t.append(u);
			}

			@Override
			public BinaryOperator<StringBuilder> combiner() {
				return (t, u) -> t.append(u);
			}

			@Override
			public Function<StringBuilder, String> finisher() {
				return t -> t.append("</table>").toString();
			}

			@Override
			public Set<Characteristics> characteristics() {
				return new TreeSet<>();
			}

		};
	}
}

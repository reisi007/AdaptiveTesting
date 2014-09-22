package at.reisisoft.jku.ce.adaptivelearning.topic.accounting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.ui.Table;

public class AccountingDataProvider {

	private final List<String[]> data = new ArrayList<>();
	private static AccountingDataProvider dataProvider = null;

	public static AccountingDataProvider getInstance() {
		if (dataProvider == null) {
			dataProvider = new AccountingDataProvider();
		}
		return dataProvider;
	}

	public AccountingDataProvider() {
		// Example data
		data.add(new String[] { "0145", "Vorrat Eisen" });
		data.add(new String[] { "2323", "Vorsteuer" });
	}

	public Table toHtmlTable() {
		Table table = new Table();
		table.addContainerProperty("Kontonummer", String.class, null);
		table.addContainerProperty("Kontenname", String.class, null);
		for (int i = 0; i < data.size(); i++) {
			table.addItem(data.get(i), i + 1);
		}
		table.setPageLength(table.size());
		table.setSizeFull();
		return table;
	}

	public String[] getAllAccountNames() {
		return data.stream().map(array -> array[1])
				.collect(Collectors.toList()).toArray(new String[data.size()]);
	}
}

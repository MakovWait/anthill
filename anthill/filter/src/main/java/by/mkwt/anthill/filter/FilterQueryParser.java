package by.mkwt.anthill.filter;

import java.util.HashMap;
import java.util.Map;

public class FilterQueryParser {

	public Map<String, FilterBatch> parse(Map<String, String> query) {
		Map<String, FilterBatch> filters = new HashMap<>();

		query.forEach((k, v) -> {
			if (k.startsWith("filter")) {
				String[] r = k.split("\\[");
				if (r.length > 2) {
					String name = r[1].substring(0, r[1].length() - 1);

					if (filters.get(name) == null) {
						filters.put(name, new FilterBatch(name));
					}

					FilterUnit filterUnit = new FilterUnit(r[2].substring(0, r[2].length() - 1), v);

					filters.get(name).addFilterUnit(filterUnit);
				}
			}
		});

		return filters;
	}

}

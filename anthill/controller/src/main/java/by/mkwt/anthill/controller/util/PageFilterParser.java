package by.mkwt.anthill.controller.util;

import java.util.Map;

import by.mkwt.anthill.dao.util.Page;
import by.mkwt.anthill.dao.util.Page.SortDirection;

public class PageFilterParser {

	public static Page getFromFilter(Map<String, String> filter) {
		Page page;
		
		if (filter.containsKey("page") && filter.containsKey("size")) {
			page = new Page(Integer.valueOf(filter.get("page")), Integer.valueOf(filter.get("size")));
		} else {
			page = new Page();
		}
		
		if (filter.containsKey("orderBy")) {
			page.setSortFieldName(filter.get("orderBy"));
		} else if (filter.containsKey("orderByDesc")) {
			page.setSortFieldName(filter.get("orderByDesc"));
			page.setSortDirection(SortDirection.DESC);
		}
		
		return page;
	}
	
}

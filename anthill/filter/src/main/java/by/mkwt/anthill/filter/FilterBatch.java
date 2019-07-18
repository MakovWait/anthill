package by.mkwt.anthill.filter;

import java.util.ArrayList;
import java.util.List;

import by.mkwt.anthill.filter.FilterUnit.OperationType;
import by.mkwt.anthill.filter.exception.FilterException;

public class FilterBatch {

	private String name;
	private List<FilterUnit> filterUnits = new ArrayList<FilterUnit>();

	public FilterBatch(String name) {
		this.name = name;
	}

	public boolean isAndTypeUnits() throws FilterException {
		int lt = 0;
		int gt = 0;
		
		for(FilterUnit unit : filterUnits) {
			if (unit.getOperationType().equals(OperationType.lt)) {
				lt++;
				if (lt >= 2) {
					throw new FilterException("More than 2 lt operations");
				}
			} else if (unit.getOperationType().equals(OperationType.gt)) {
				gt++;
				if (gt >= 2) {
					throw new FilterException("More than 2 gt operations");
				}
			}
		}
		
		return gt==1&&lt==1;
	}
	
	public String getName() {
		return name;
	}

	public List<FilterUnit> getFilterUnits() {
		return filterUnits;
	}

	public void addFilterUnit(FilterUnit filterUnit) {
		filterUnits.add(filterUnit);
	}

	@Override
	public String toString() {
		return "FilterBatch [name=" + name + ", filterUnits=" + filterUnits + "]";
	}

}

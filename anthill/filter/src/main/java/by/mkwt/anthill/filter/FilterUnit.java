package by.mkwt.anthill.filter;

import by.mkwt.anthill.filter.exception.FilterException;

public class FilterUnit {

	public enum OperationType {
		eq, lt, gt
	}

	private String value;
	private OperationType operationType;

	public FilterUnit(OperationType operationType, String value) {
		this.operationType = operationType;
		this.value = value;
	}

	public FilterUnit(String operationType, String value) {
		this(OperationType.valueOf(operationType), value);
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public void setOperationType(String operationType) throws FilterException {
		try {
			this.operationType = OperationType.valueOf(operationType);
		} catch (Exception e) {
			throw new FilterException("Operation type[" + operationType + "] not supported");
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "FilterUnit [" + "operationType=" + operationType + ", value=" + value + "]";
	}

}

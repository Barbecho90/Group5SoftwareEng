package clientModel;

import model.AbstractTable;

public class Table extends AbstractTable{
	private static final long serialVersionUID = 1L;
	private String tableId;
	
	private static Table instance;
	
	private Table() {
		super(null);
	}
	
	public static Table getInstance() {
		if (instance == null) {
			instance = new Table();
		}
		
		return instance;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	public String getTableId() {
		return this.tableId;
	}
}

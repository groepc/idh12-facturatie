/**
 * 
 */
package Systeem.Verzekeringen.Presentation.customModels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * @author Gregor
 *
 */
public abstract class CustomObjectTableModel<T> extends AbstractTableModel {

	protected List<T> lijst;
	protected String[] columnNames;

	/**
	 * 
	 */
	public CustomObjectTableModel(String[] columnNames) {
		super();
		clear();
		this.columnNames = columnNames;
	}

	public CustomObjectTableModel(String[] columnNames, List<T> behandelijst) {
		this(columnNames);
		behandelijst.addAll(behandelijst);
	}

	public void addBehandeling(T behandeling) {
		lijst.add(behandeling);
		fireTableDataChanged();
	}

	public void addBehandelingen(List<T> behandelijst) {
		lijst.addAll(behandelijst);
		fireTableDataChanged();
	}

	public void removeBehandeling(T behandeling) {
		lijst.remove(behandeling);
		fireTableDataChanged();
	}

	public void removeBehandelingRij(int rijnummer) {
		lijst.remove(rijnummer);
		fireTableDataChanged();
	}

	public void clear() {
		lijst = new ArrayList<>(20);
		fireTableDataChanged();
	}

	public T getRowItem(int rowNumber) {
		return lijst.get(rowNumber);
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return lijst.size();
	}

	public int getColumnCount() {
		return columnNames.length;
	};

	@Override
	public abstract Object getValueAt(int rowIndex, int columnIndex);

}

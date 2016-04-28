/**
 * 
 */
package Systeem.Verzekeringen.Presentation.customRenderers;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import Systeem.BusinessDomain.Behandelingtraject;
import Systeem.BusinessDomain.Verzekering;

/**
 * @author Gregor
 *
 */
public class BehandelingListRenderer extends DefaultListCellRenderer {

	/* (non-Javadoc)
	 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
	 */
	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Behandelingtraject behandeling = (Behandelingtraject) value;
		setText(behandeling.getNaam());
		return this;
	}

}

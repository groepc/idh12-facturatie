/**
 * 
 */
package Systeem.Verzekeringen.Presentation.customRenderers;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import Systeem.BusinessDomain.Verzekering;

/**
 * @author Gregor
 *
 */
public class VerzekeringListRenderer extends DefaultListCellRenderer {
	/** Creates a new instance of LocaleRenderer */
	public VerzekeringListRenderer() {
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Verzekering verzekering = (Verzekering) value;
		setText(verzekering.getNaam());
		return this;
	}
}

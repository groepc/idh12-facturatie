/**
 * 
 */
package Systeem.Verzekeringen.Presentation.customRenderers;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import Systeem.BusinessDomain.Verzekeringsmaatschappij;

/**
 * @author Gregor
 *
 */
public class VerzekeringmaatschappijListRenderer extends DefaultListCellRenderer {
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Verzekeringsmaatschappij maatschappij = (Verzekeringsmaatschappij) value;
		setText(maatschappij.getNaam());
		return this;
	}
}

/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */

package Systeem.Datastorage.BaseClasses;

import Systeem.Datastorage.Interfaces.IEditableDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLEditableDAOBase.
 * 
 * @author Jermey
 * @param <T>
 *            the generic type
 * @param <PrimaryKeyType>
 *            the generic type
 */
public abstract class XMLEditableDAOBase<T, PrimaryKeyType> extends XMLReadableDAOBase<T, PrimaryKeyType>
		implements IEditableDAO<T> {

	/**
	 * Instantiates a new XML editable dao base.
	 *
	 * @param bestandpad
	 *            the bestandpad
	 * @param naam
	 *            the naam
	 */
	public XMLEditableDAOBase(final String bestandpad, final String naam) {
		super(bestandpad, naam);
	}
}

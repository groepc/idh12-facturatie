/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */

package Systeem.Datastorage.BaseClasses;

import Systeem.Datastorage.Interfaces.IReadDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLDeletableDAOBase.
 * 
 * @author Jermey
 * @param <T>
 *            the generic type
 * @param <PrimaryKeyType>
 *            the generic type
 */
public abstract class XMLReadableDAOBase<T, PrimaryKeyType> extends XMLBase<T>
		implements
		IReadDAO<T, PrimaryKeyType> {

	/**
	 * Instantiates a new XML deletable dao base.
	 *
	 * @param bestandpad
	 *            the bestandpad
	 * @param name
	 *            the name
	 */
	public XMLReadableDAOBase(final String bestandpad, final String name) {
		super(bestandpad, name);

	}
}

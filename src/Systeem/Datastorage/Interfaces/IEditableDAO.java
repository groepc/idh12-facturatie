/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Datastorage.Interfaces;

import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Interface IEditableDAO.
 *
 * @param <T>
 *            the generic type
 */
public interface IEditableDAO<T> {

	/**
	 * Opslaan.
	 *
	 * @param object
	 *            the object meant to be saved
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	void opslaan(T object) throws IOException;

}
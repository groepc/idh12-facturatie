/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Datastorage.Interfaces;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDeletableDAO.
 *
 * @param <T>
 *            the generic type
 */
public interface IDeletableDAO<T> {

	/**
	 * Verwijderen.
	 *
	 * @param object
	 *            the object that is intended for deletion
	 */
	void verwijderen(T object);

}

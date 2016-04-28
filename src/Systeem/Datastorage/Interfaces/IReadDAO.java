/**
 * @creator B4
 * @date    4-nov-2014
 * @version 7.1
 */
package Systeem.Datastorage.Interfaces;

import java.io.IOException;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface IReadDAO.
 *
 * @param <T>
 *            the generic type
 * @param <PrimaryKeyType>
 *            the generic type
 */
public interface IReadDAO<T, PrimaryKeyType> {

	/**
	 * The Enum SearchLevel.
	 */
	public enum SearchLevel {

		/**  The contains. */
		CONTAINS,
		/** The startswith. */
		STARTSWITH,
		/** The complete. */
		COMPLETE
	}

	/**
	 * The Enum SearchField.
	 */
	public enum SearchField {

		/** The first. */
		FIRST,
		/** The second. */
		SECOND,
		/** The third. */
		THIRD
	}

	/**
	 * Geef. Gives 1 object
	 * 
	 * @param key
	 *            the primarykey
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	T geef(PrimaryKeyType key) throws IOException;

	/**
	 * Geef meerdere. Gives a list with object that correspond to the zoekString
	 *
	 * @param zoekString
	 *            the zoek string
	 * @param field
	 *            the field
	 * @param searchLevel
	 *            the search level
	 * @return the array list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	List<T> geefMeerdere(String zoekString, SearchField field, SearchLevel searchLevel) throws IOException;

	/**
	 * Geef alles. Gives everything
	 * 
	 * @return the array list
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	List<T> geefAlles() throws IOException;
}
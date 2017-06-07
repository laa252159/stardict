package com.orangereading.stardict.io;

import java.io.IOException;

import com.orangereading.stardict.domain.DictionaryIndexItem;
import com.orangereading.stardict.domain.DictionaryItem;

/**
 * 
 * Read StarDict .dict file.
 * 
 * @author sean
 *
 */
public interface DictionaryReader {

	/**
	 * 
	 * Read StarDict .dict file.
	 * 
	 * @param indexItem
	 *            index info
	 * 
	 * @return dictionary item
	 */
	public DictionaryItem read(final DictionaryIndexItem indexItem) throws IOException;

}

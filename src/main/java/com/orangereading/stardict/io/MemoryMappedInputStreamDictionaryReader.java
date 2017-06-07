package com.orangereading.stardict.io;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;

import com.orangereading.stardict.domain.DictionaryIndexItem;
import com.orangereading.stardict.domain.DictionaryItem;
import com.orangereading.stardict.parser.DictionaryParser;

/**
 * 
 * Read StarDict .dict input steam.<br>
 * It will fully read the input stream and cached the contents in memory.<br>
 * 
 * @author sean
 *
 */
public class MemoryMappedInputStreamDictionaryReader implements DictionaryReader {

	private final byte[] content;

	private final DictionaryParser parser;

	public MemoryMappedInputStreamDictionaryReader(final DictionaryParser parser, final InputStream in) {
		this.parser = parser;

		// read stream fully to memory
		try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
			byte[] buffer = new byte[0xFFFF];
			for (int len; (len = in.read(buffer)) != -1;)
				os.write(buffer, 0, len);

			os.flush();
			this.content = os.toByteArray();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public DictionaryItem read(final DictionaryIndexItem indexItem) {
		final byte[] data = Arrays.copyOfRange(content, indexItem.getOffset().intValue(),
				indexItem.getOffset().intValue() + indexItem.getSize());
		return parser.parse(indexItem, data);
	}

}

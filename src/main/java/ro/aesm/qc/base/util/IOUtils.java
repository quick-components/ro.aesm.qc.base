package ro.aesm.qc.base.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {

	public static String toString(InputStream inputStream) throws IOException {
		return toString(inputStream, "UTF-8");
	}

	public static String toString(InputStream inputStream, String charsetName) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		for (int length; (length = inputStream.read(buffer)) != -1;) {
			result.write(buffer, 0, length);
		}
		return result.toString(charsetName);
	}

}

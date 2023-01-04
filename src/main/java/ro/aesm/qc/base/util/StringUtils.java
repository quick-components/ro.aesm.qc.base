package ro.aesm.qc.base.util;

public class StringUtils {

	public static boolean isEmpty(String s) {
		if (s == null || s.equals("")) {
			return true;
		}
		return false;
	}
}

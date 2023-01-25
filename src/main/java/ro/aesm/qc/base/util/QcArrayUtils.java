package ro.aesm.qc.base.util;

import java.lang.reflect.Array;
import java.util.Arrays;

public class QcArrayUtils {

	public static <T> T[] copyRange(T[] src, int from, int to) {
		return copyRange(src, from, to, 1);
	}

	public static <T> T[] copyRange(T[] src, int from, int to, int step) {
		if (src.length == 0 || src.length <= from || src.length <= to) {

			return (T[]) Array.newInstance(src.getClass().getComponentType(), 0);
		}
		int newLength = (to >= from) ? to - from : from - to;
		newLength++; // inclusive both ends
		@SuppressWarnings("unchecked")
		T[] result = (T[]) Array.newInstance(src.getClass().getComponentType(), newLength);
		if (to >= from) {
			int j = 0;
			for (int i = from; i <= to; i = i + step) {
				result[j] = src[i];
				j++;
			}
		} else {
			int j = 0;
			for (int i = from; i >= to; i = i - step) {
				result[j] = src[i];
				j++;
			}
		}
		return result;
	}

	public static <T> void reverse(T[] array) {
		int len = array.length;
		T temp = null;
		for (int i = 0; i < len / 2; i++) {
			int dest = len - i - 1;
			temp = array[i];
			array[i] = array[dest];
			array[dest] = temp;
		}
		//System.out.println("Reversed Array: \n" + Arrays.toString(array));
	}

}

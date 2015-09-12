package bean;

import java.util.Arrays;

/**
 * @author elvis.xu
 * @since 2015-09-13 00:19
 */
public class A implements IA {
	private byte ab = (byte) 1;
	private int[] ais = new int[] { 1, 2, 3 };
	private String aString = "bString";

	@Override
	public String toString() {
		return "A{" + "ab=" + ab + ", ais=" + Arrays.toString(ais) + ", aString='" + aString + '\'' + '}';
	}
}

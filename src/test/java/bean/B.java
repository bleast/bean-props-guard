package bean;

import java.util.Arrays;

import com.xjd.bpg.annotation.GuardProp;

/**
 * @author elvis.xu
 * @since 2015-09-13 00:19
 */
public class B extends A {
	private byte bb = (byte) 1;
	@GuardProp({ "1", "A" })
	private int[] bis = new int[] { 1, 2, 3 };
	private String bString = "bString";

	@Override
	public String toString() {
		return "B{" + "bb=" + bb + ", bis=" + Arrays.toString(bis) + ", bString='" + bString + '\'' + ", super=" +  super.toString()
				+ '}';
	}
}

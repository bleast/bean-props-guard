package bean;

import com.xjd.bpg.annotation.GuardProp;

/**
 * @author elvis.xu
 * @since 2015-09-13 00:19
 */
public class C extends B {
	@GuardProp("1")
	private int ci = 90;
	private String cString = "cString";
	@GuardProp({ "1", "B" })
	private IA ib = new B();
	@GuardProp({ "1", "A" })
	private IA ia = new A();

	private IA it = this;
	@GuardProp(mode = GuardProp.GuardMode.EXCLUDE)
	private Integer ei = 9;
	@GuardProp(value = "A", mode = GuardProp.GuardMode.EXCLUDE)
	private Integer eA = 9;

	@Override
	public String toString() {
		return "C{" + "ci=" + ci + ", cString='" + cString + '\'' + ", ib=" + ib + ", ia=" + ia + ", super="
				+ super.toString() + '}';
	}

}

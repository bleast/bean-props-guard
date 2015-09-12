package bean;

import com.xjd.bpg.annotation.GuardProp;

/**
 * @author elvis.xu
 * @since 2015-09-13 01:18
 */
public class TestBean {
	private String name = "this is name";
	private int age = 20;
	@GuardProp(mode = GuardProp.GuardMode.EXCLUDE)
	private String pass = "this is pass";
	@GuardProp("self")
	private String mobile = "this is mobile";
	@GuardProp("self")
	private String address = "this is address";

	@Override
	public String toString() {
		return "TestBean{" + "name='" + name + '\'' + ", age=" + age + ", pass='" + pass + '\'' + ", mobile='" + mobile
				+ '\'' + ", address='" + address + '\'' + '}';
	}
}

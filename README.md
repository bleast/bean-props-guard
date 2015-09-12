# bean-props-guard
bean-props-guard is an utility to prevent the sensitive or redundancy properties of a bean from transferring to others.


### Usage
```java

// Define TestBean using annotation GuardProp
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

// Use Guards.guard(Object beanObj, String... tokens) to do the guard action

TestBean bean1 = new TestBean();
Guards.guard(bean1);
System.out.println(bean1);
TestBean bean2 = new TestBean();
Guards.guard(bean2, "self");
System.out.println(bean2);

// The results as follow

TestBean{name='this is name', age=20, pass='null', mobile='null', address='null'}
TestBean{name='this is name', age=20, pass='null', mobile='this is mobile', address='this is address'}
```
* ``@GuardProp(mode = GuardProp.GuardMode.EXCLUDE)`` indicate that the 'pass' property should always be guarded (set value to null)
* ``@GuardProp("self")`` indicate that the annotated properties should be guarded unless the 'self' token is given

More information please sea the Example.java
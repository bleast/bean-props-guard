import java.util.Arrays;

import bean.C;
import bean.IA;
import bean.TestBean;

import com.xjd.bpg.Guards;

/**
 * @author elvis.xu
 * @since 2015-09-13 00:18
 */
public class Example {
	public static void main(String[] args) {
		{
			TestBean bean1 = new TestBean();
			Guards.guard(bean1);
			System.out.println(bean1);
			TestBean bean2 = new TestBean();
			Guards.guard(bean2, "self");
			System.out.println(bean2);
		}

		{
			IA[] array = new IA[] { new C() };
			Guards.guard(array);
			System.out.println(Arrays.toString(array));
		}
		{
			IA[] array = new IA[] { new C() };
			Guards.guard(array, "B");
			System.out.println(Arrays.toString(array));
		}
		{
			IA[] array = new IA[] { new C() };
			Guards.guard(array, "A", "B");
			System.out.println(Arrays.toString(array));
		}

		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			IA[] array = new IA[] { new C() };
		}
		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			IA[] array = new IA[] { new C() };
			Guards.guard(array, "B");
		}
		System.out.println(System.currentTimeMillis() - start);
	}

}

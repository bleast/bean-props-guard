package com.xjd.bpg;

import java.util.HashSet;

import com.xjd.bpg.core.Guarder;

/**
 * @author elvis.xu
 * @since 2015-09-12 21:16
 */
public abstract class Guards {
	private static Guarder guarder = new Guarder();

	public static void guard(Object beanObj, String... tokens) {
		guarder.guard(beanObj, new HashSet<Object>(), tokens);
	}
}

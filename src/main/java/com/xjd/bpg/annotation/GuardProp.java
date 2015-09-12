package com.xjd.bpg.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author elvis.xu
 * @since 2015-09-12 21:12
 */
@Target({ ElementType.FIELD })
@Retention(RUNTIME)
@Inherited
public @interface GuardProp {
	String[] value() default {};

	GuardMode mode() default GuardMode.INCLUDE;

	public static enum GuardMode {
		INCLUDE, EXCLUDE;
	}
}

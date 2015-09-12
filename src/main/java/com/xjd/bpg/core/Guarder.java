package com.xjd.bpg.core;

import java.lang.reflect.Field;
import java.util.Set;

import com.xjd.bpg.annotation.GuardProp;

/**
 * @author elvis.xu
 * @since 2015-09-12 21:18
 */
public class Guarder {
	public void guard(Object beanObj, Set<Object> guardedObjs, String... tokens) {
		if (beanObj == null)
			return;
		Class clazz = beanObj.getClass();
		guard(clazz, false, beanObj, guardedObjs, tokens);
	}

	public void guard(Class clazz, boolean guardSuperClazz, Object beanObj, Set<Object> guardedObjs, String... tokens) {
		if (clazz == null || beanObj == null)
			return;
		if (!guardSuperClazz && guardedObjs.contains(beanObj))
			return;
		if (clazz.getName().startsWith("java") || clazz.isInterface() || noNeedGuardClass(clazz))
			return;
		guardedObjs.add(beanObj);

		if (clazz.isArray()) { // 数组
			Class compClazz = clazz.getComponentType();
			if (noNeedGuardClass(compClazz))
				return;
			Object[] array = (Object[]) beanObj;
			for (Object item : array) {
				guard(item, guardedObjs, tokens);
			}

		} else if (Iterable.class.isAssignableFrom(clazz)) { // 集合 TODO check其它情况
			for (Object item : (Iterable) beanObj) {
				guard(item, guardedObjs, tokens);
			}

		} else { // 一般对象
			for (Field field : clazz.getDeclaredFields()) {
				guardField(field, beanObj, guardedObjs, tokens);
			}
			Class superClazz = clazz.getSuperclass();
			guard(superClazz, true, beanObj, guardedObjs, tokens);
		}
	}

	protected void guardField(Field field, Object beanObj, Set<Object> guardedObjs, String... tokens) {
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		GuardProp guardProp = field.getAnnotation(GuardProp.class);
		if (needCover(guardProp, tokens)) {
			coverFiled(field, beanObj);
		} else {
			Class clazz = field.getType();
			if (noNeedGuardClass(clazz))
				return;
			try {
				Object fieldVal = field.get(beanObj);
				guard(fieldVal, guardedObjs, tokens);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	protected boolean noNeedGuardClass(Class<?> clazz) {
		if (clazz == null || clazz.isPrimitive() || clazz.isEnum() || clazz.isAnnotation())
			return true;
		return false;
	}

	protected boolean needCover(GuardProp guardProp, String... tokens) {
		if (guardProp == null)
			return false;
		GuardProp.GuardMode mode = guardProp.mode();
		String[] fieldTokens = guardProp.value();
		if (mode == GuardProp.GuardMode.INCLUDE) {
			if (fieldTokens == null || fieldTokens.length == 0) {
				return false; // 所有TOKEN可用
			} else {
				if (containsAny(fieldTokens, tokens)) {
					return false;
				} else {
					return true;
				}
			}
		} else {
			if (fieldTokens == null || fieldTokens.length == 0) {
				return true; // 所有TOKEN无用
			} else {
				if (containsAny(fieldTokens, tokens)) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	protected boolean containsAny(String[] array, String... values) {
		if (array == null || array.length == 0)
			return false;
		if (values == null || values.length == 0)
			return false;
		for (String item : array) {
			for (String value : values) {
				if (item == value || (item != null && item.equals(value))) {
					return true;
				}
			}
		}
		return false;
	}

	protected void coverFiled(Field field, Object bean) {
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		Class clazz = field.getType();
		try {
			if (clazz.isPrimitive()) {
				if (clazz.equals(boolean.class)) {
					field.setBoolean(bean, true);
				} else if (clazz.equals(byte.class)) {
					field.setByte(bean, (byte) 0);
				} else if (clazz.equals(char.class)) {
					field.setChar(bean, ' ');
				} else if (clazz.equals(short.class)) {
					field.setShort(bean, (short) 0);
				} else if (clazz.equals(int.class)) {
					field.setInt(bean, 0);
				} else if (clazz.equals(long.class)) {
					field.setLong(bean, 0L);
				} else if (clazz.equals(float.class)) {
					field.setFloat(bean, 0F);
				} else if (clazz.equals(double.class)) {
					field.setDouble(bean, 0D);
				}
			} else {
				field.set(bean, null);
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}

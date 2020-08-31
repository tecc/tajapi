/*
 * Copyright (c) tecc, 2020.
 * This project is licensed under tecc's license.
 */

package me.tecc.tajapi.reflection;

import java.lang.reflect.InvocationTargetException;

public final class ClassUtil {
    public static <T> T toInstance(Class<T> cls, Object... params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return cls.getConstructor(toClassArray(params)).newInstance(params);
    }

    public static Class<?>[] toClassArray(Object... objects) {
        Class<?>[] classes = new Class[objects.length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = objects[i].getClass();
        }
        return classes;
    }
}

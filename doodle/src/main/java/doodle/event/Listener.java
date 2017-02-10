package doodle.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Listener {
    private Object instance;
    private Method method;

    Listener(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }

    Listener(Object instance, Method method, boolean setAccessible) {
        this(instance, method);
        this.method.setAccessible(setAccessible);
    }

    void invoke(Object value) {
        try {
            method.invoke(instance, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("UH OH!");
        }
    }
}

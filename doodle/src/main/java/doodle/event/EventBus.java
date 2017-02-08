package doodle.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EventBus {
    INSTANCE;

    private static final List<Listener> EMPTY = new ArrayList<>();

    private Map<Class<?>, List<Listener>> listenerMap;

    EventBus() {
        listenerMap = new HashMap<>();
    }

    public static void subscribe(Object object) {
        INSTANCE.doSubscribe(object);
    }

    public static void post(Object event) {
        INSTANCE.doPost(event);
    }

    private void doSubscribe(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }

        findSubscriptionMethods(object);
    }

    private void doPost(Object event) {
        List<Listener> listeners = listenerMap.getOrDefault(event.getClass(), EMPTY);

        listeners.forEach(listener -> listener.invoke(event));
    }

    private void findSubscriptionMethods(Object object) {
        Method[] methods = object.getClass().getMethods();

        for (Method method : methods) {
            checkMethodForSubscription(object, method);
        }
    }

    private void checkMethodForSubscription(Object object, Method method) {
        Annotation subscribe = method.getAnnotation(Subscribe.class);

        if (subscribe != null) {
            checkMethodForValidParameters(object, method);
        }
    }

    private void checkMethodForValidParameters(Object object, Method method) {
        Parameter[] parameters = method.getParameters();

        if (parameters.length != 1) {
            throw new IllegalArgumentException("Subscription handlers can only have one parameter");
        }

        addListener(object, method, parameters[0]);
    }

    private void addListener(Object object, Method method, Parameter parameter) {
        Class<?> clazz = parameter.getType();

        listenerMap.computeIfAbsent(clazz, v -> new ArrayList<>(1))
                .add(new Listener(object, method));
    }

    private class Listener {
        private Object instance;
        private Method method;

        Listener(Object instance, Method method) {
            this.instance = instance;
            this.method = method;
        }

        void invoke(Object value) {
            try {
                method.invoke(instance, value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}

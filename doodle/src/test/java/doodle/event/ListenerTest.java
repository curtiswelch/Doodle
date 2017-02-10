package doodle.event;

import org.junit.Test;

import java.lang.reflect.Method;

public class ListenerTest {

    @Test(expected = RuntimeException.class)
    public void throwsExceptionOnIncompatibleTypes() {
        Method method = getMethod(StupidClassA.class);

        Listener listener = new Listener(this, method);

        listener.invoke(null);

    }

    @Test(expected = RuntimeException.class)
    public void throwsExceptionWhenAccessibilityIsPrivateWithoutSetAccessibleFlag() {
        Method method = getMethod(StupidClassB.class);

        Listener listener = new Listener(new StupidClassB(), method);

        listener.invoke(null);
    }

    @Test
    public void worksWhenAccessibilityIsPrivateWithSetAccessibleFlag() {
        Method method = getMethod(StupidClassB.class);

        Listener listener = new Listener(new StupidClassB(), method, true);

        listener.invoke(null);
    }

    private Method getMethod(Class<?> clazz) {
        return clazz.getDeclaredMethods()[0];
    }

    private class StupidClassA {
        public void method() {
        }
    }

    private class StupidClassB {
        private void method(Object arg) {
        }
    }
}

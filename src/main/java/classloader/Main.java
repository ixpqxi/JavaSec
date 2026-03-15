package classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ClassLoaderTest loader = new ClassLoaderTest();

        Class<?> helloClass = loader.loadClass("com.anbai.sec.classloader.TestHelloWorld");

        Object object = helloClass.newInstance();

        Method method = object.getClass().getMethod("hello");

        String result = (String) method.invoke(object);

        System.out.println(result);
    }
}

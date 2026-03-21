package reflection;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        Class runtimeClass1 = Class.forName("java.lang.Runtime");

        Method getRuntimeMethod = runtimeClass1.getMethod("getRuntime");

        Object runtimeInstance = getRuntimeMethod.invoke(null);

        Method execMethod = runtimeClass1.getMethod("exec", String.class);

        Process process = (Process) execMethod.invoke(runtimeInstance, "calc.exe");

//        InputStream in = process.getInputStream();
//
//        System.out.println(IOUtils.toString(in, "UTF-8"));


    }
}

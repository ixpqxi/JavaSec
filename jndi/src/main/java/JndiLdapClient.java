import javax.naming.InitialContext;
import java.lang.reflect.Method;

public class JndiLdapClient {
    public static void main(String[] args) throws Exception {
        String ldapUrl = args.length > 0 ? args[0] : "ldap://localhost:8091/RemoteObject";
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");

        InitialContext ctx = new InitialContext();
        Object obj = ctx.lookup(ldapUrl);

        System.out.println("lookup result: " + obj.getClass().getName());
        Method sayHello = obj.getClass().getMethod("sayHello");
        sayHello.invoke(obj);
    }
}

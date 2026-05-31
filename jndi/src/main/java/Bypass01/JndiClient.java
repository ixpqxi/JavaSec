package Bypass01;

import javax.naming.InitialContext;

public class JndiClient {
    public static void main(String[] args) throws Exception {
        String url = args.length > 0 ? args[0] : "rmi://localhost:1099/RemoteObject";

        InitialContext initialContext = new InitialContext();
        Object object = initialContext.lookup(url);

        System.out.println("lookup result: " + object.getClass().getName());
        System.out.println("jndi.bypass marker: " + System.getProperty("jndi.bypass"));
    }
}

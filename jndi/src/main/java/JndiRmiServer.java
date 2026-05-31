import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class JndiRmiServer {
    public static void main(String[] args) throws NamingException, RemoteException {
        InitialContext initialContext = new InitialContext();
        Registry registry = LocateRegistry.createRegistry(1099);
        RemoteCmd cli = new RemoteCli();
        initialContext.rebind("rmi://localhost:1099/cli", cli);
    }
}

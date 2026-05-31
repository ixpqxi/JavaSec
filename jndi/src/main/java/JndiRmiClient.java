import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

public class JndiRmiClient {
    public static void main(String[] args) throws NamingException, IOException {
        InitialContext initialContext = new InitialContext();
        RemoteCmd cli = (RemoteCmd) initialContext.lookup("rmi://localhost:1099/cli");
        System.out.println(cli.getRemoteHostName());
    }
}

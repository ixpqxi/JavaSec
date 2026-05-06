import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class RemoteCliClient {
    public static void main(String[] args) throws IOException, NotBoundException {
        String serverAddr = args[0];
        String cmd = args[1];

        RemoteCmd cli = (RemoteCmd) Naming.lookup(String.format("rmi://%s/cli", serverAddr));
        System.out.println("==================================================================");
        System.out.println(cli.getRemoteHostName());
        System.out.println("==================================================================");
        cli.execute(cmd);
    }
}

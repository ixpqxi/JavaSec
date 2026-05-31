import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteCli extends UnicastRemoteObject implements RemoteCmd {
    public RemoteCli() throws RemoteException {
    }

    @Override
    public void execute(String cmd) throws IOException, RuntimeException {
        System.out.println("Executing command: " + cmd);
        Runtime.getRuntime().exec(cmd);
    }

    @Override
    public String getRemoteHostName() throws IOException, RuntimeException {
        Process process = Runtime.getRuntime().exec("hostname");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            output.append(line);
        }
        return output.toString();
    }
}

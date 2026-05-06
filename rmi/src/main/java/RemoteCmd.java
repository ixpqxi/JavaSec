import java.io.IOException;
import java.rmi.Remote;

public interface RemoteCmd extends Remote {
    String getRemoteHostName() throws IOException;
    void execute(String cmd) throws IOException, RuntimeException;
}
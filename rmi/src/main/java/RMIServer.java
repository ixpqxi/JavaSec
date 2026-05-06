import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        // 创建远程调用对象
        RemoteCli cli = new RemoteCli();

        // 创建RMI注册表
        LocateRegistry.createRegistry(1099);

        // 绑定RMI服务
        Naming.rebind("rmi://0.0.0.0:1099/cli", cli);
    }
}

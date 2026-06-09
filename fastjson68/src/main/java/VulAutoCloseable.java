import java.io.IOException;

public class VulAutoCloseable implements AutoCloseable {
    public VulAutoCloseable(String cmd) {
        try {
            System.out.println("exec: " + cmd);
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {

    }
}

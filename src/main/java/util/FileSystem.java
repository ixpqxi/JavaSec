package util;

import java.io.File;
import java.io.Serializable;

public interface FileSystem extends Serializable {
    String[] list(File file);
}

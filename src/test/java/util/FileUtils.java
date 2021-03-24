package util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.Objects;

public class FileUtils {

    public static final String SEPARATOR = FileSystems.getDefault().getSeparator();

    private FileUtils() {
    }

    public static String getAbsoluteUrlFromResourceFile(String fileName) {
        try {
            URL is = ClassLoader.getSystemClassLoader().getResource(fileName);
            File file = Paths.get(Objects.requireNonNull(is).toURI()).toFile();
            return file.getAbsolutePath();
        } catch (URISyntaxException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Archivo '%s' no encontrado en resources del proyecto", fileName));
        }
    }

}

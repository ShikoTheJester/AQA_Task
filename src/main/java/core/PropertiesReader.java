package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesReader {private static Properties properties = null;
    private static final String configFilePath = "src/test/resources/application.properties";

    private static Properties readPropertiesFromFile() {
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(new InputStreamReader(new FileInputStream(new File(configFilePath)), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String get(String key) {
        if (properties == null) {
            properties = readPropertiesFromFile();
        }
        return properties.getProperty(key);
    }
}

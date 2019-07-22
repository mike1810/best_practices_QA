package readResource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader{

        private static Properties properties = new Properties();
        private static FileInputStream ip;

    static {
        try {
            ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String readDriverPath() throws IOException {
        properties.load(ip);
        return properties.getProperty("driverPath");
    }

    public static String readUrl() throws IOException {
        properties.load(ip);
        return properties.getProperty("url");
    }

    public static long readImplicitlyWait() throws IOException {
        properties.load(ip);
        long wait = Long.parseLong(properties.getProperty("implicitlyWait"));
        return wait;
    }

    public static String readLog() throws IOException {
        properties.load(ip);
        String log = properties.getProperty("log");
        return log;
    }

    public static String readJson() throws IOException {
        properties.load(ip);
        String json = properties.getProperty("json");
        return json;
    }

}

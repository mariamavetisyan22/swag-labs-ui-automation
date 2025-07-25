package configs;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Configuration {
    public static final String CHROME_BROWSER = "chrome";
    public static final String FIREFOX_BROWSER = "firefox";
    public static final String EDGE_BROWSER = "edge";
    public static final String SAFARI_BROWSER = "safari";

    public static final String DEMO = "demo";

    public static final String BROWSER = System.getProperty("browser", CHROME_BROWSER);

    public static final String WEB_PROP_PATH = "src/main/resources/configs/demo.properties";

    private static final Properties properties = new Properties();

    @Getter
    public static String STANDARD_USERNAME;
    @Getter
    public static String LOCKED_OUT_USERNAME;
    @Getter
    public static String PROBLEM_USERNAME;
    @Getter
    public static String PERFORMANCE_USERNAME;
    @Getter
    public static String ERROR_USERNAME;
    @Getter
    public static String VISUAL_USERNAME;
    @Getter
    public static String PASSWORD;
    @Getter
    private static String WEB_URL;

    @SneakyThrows
    public static void loadPropertyFile() {
        try (InputStream inputStream = Files.newInputStream(Paths.get(WEB_PROP_PATH))) {
            properties.load(inputStream);
            WEB_URL = properties.getProperty("demo.url");
            STANDARD_USERNAME = properties.getProperty("standard.username");
            LOCKED_OUT_USERNAME = properties.getProperty("locked.out.username");
            LOCKED_OUT_USERNAME = properties.getProperty("problem.username");
            LOCKED_OUT_USERNAME = properties.getProperty("performance.username");
            LOCKED_OUT_USERNAME = properties.getProperty("error.username");
            LOCKED_OUT_USERNAME = properties.getProperty("visual.username");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }
}

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import readResource.ReadResourceFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseTest {
    static final Logger LOGGER = Logger.getLogger(BaseTest.class);

    WebDriver driver;
    Properties prop;
    DataPool dataPool;

    @BeforeClass
    public void beforeClass() throws IOException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +
                ReadResourceFile.read("driverPath.txt"));
        driver = new ChromeDriver();
        initProperties();
        driver.manage().window().maximize();
        PropertyConfigurator.configure(System.getProperty("user.dir") + prop.getProperty("log"));
    }

    private void initProperties() throws IOException{
        prop = new Properties();
        InputStream input = new FileInputStream(ReadResourceFile.read("configProperties.txt"));
        prop.load(input);
    }

    @AfterSuite
    public void afterSuite() {
        //driver.quit();
    }
}
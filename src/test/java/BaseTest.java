import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseTest {

    WebDriver driver;
    Properties prop;
    static Logger LOGGER;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        initProperties();
        driver.manage().window().maximize();
    }

    void initProperties(){
        prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("C:\\Users\\mikhail.kaliberdin\\Documents\\GitHub\\automationQA\\src\\test\\resources\\config.properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @AfterClass
    public void afterClass() {
       // driver.quit();
    }
}

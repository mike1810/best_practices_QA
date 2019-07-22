import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import readResource.ReadResourceFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public abstract class BaseTest {

    WebDriver driver;
    Properties prop;
    static Logger LOGGER;
    UserPool userPool;
    UserPool userPoolNew;

    @BeforeSuite
    protected void beforeSuite( ITestContext testContext ) {
        userPool = new UserPool();
        HashMap<String,String> parameters = new HashMap<>( testContext.getCurrentXmlTest().getAllParameters());
        userPool.processDataFile( parameters.get( "dataFile" ) );
    }

    @BeforeClass
    public void beforeClass() throws IOException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        initProperties();
        driver.manage().window().maximize();
    }

    private void initProperties() throws IOException{
        prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(ReadResourceFile.read("configProperties.txt"));
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

    @AfterSuite
    public void afterSuite() {
        driver.quit();
    }
}
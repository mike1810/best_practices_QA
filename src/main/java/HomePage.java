import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class HomePage extends Page {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String toString() {
        return "HomePage";
    }
}

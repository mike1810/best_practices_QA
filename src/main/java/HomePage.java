import org.openqa.selenium.WebDriver;


public class HomePage extends Page {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String toString() {
        return "HomePage";
    }
}

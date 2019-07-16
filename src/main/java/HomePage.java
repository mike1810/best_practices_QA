import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class HomePage extends Page {
    public HomePage(){}
    public HomePage(WebDriver driver){
        super(driver);
    }

    void checkPageTitle(){
        Assert.assertEquals(driver.getTitle(), "My Store", "not equals");
    }

    @Override
    public String toString(){
        return "HomePage";
    }
}

import lombok.Data;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

@Data
public class MyAddressesPage extends Page {
    public MyAddressesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h3")
    private List<WebElement> addressAliases;

    public void findElements(List<WebElement> webElementsList) {
        for (WebElement webElement : webElementsList) {
            //System.out.println(webElement.getText());
        }
    }

    @FindBy(xpath = "//span[contains(text(),'Add a new address')]")
    private WebElement addANewAddress;

    @FindBy(xpath = "//span[contains(text(),'Update')]")
    private WebElement updateAddress;

    @FindBy(xpath = "//span[contains(text(),'Delete')]")
    private WebElement deleteAddress;

    @FindBy(xpath = "//span[contains(text(),'Back to your account')]")
    private WebElement backToYourAccount;

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    private WebElement Home;

    void addANewAddress() {
        clickAfterWaiting(addANewAddress);
    }

    void deleteAnAddress() {
        clickAfterWaiting(deleteAddress);
    }

    void openAddressUpdatePage() {
        clickAfterWaiting(updateAddress);
    }

    void goToMyAccountPage() {
        clickAfterWaiting(backToYourAccount);
    }

    void openHomePage() {
        clickAfterWaiting(Home);
    }
}
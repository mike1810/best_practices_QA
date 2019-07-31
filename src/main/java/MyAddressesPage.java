import lombok.Data;
import models.Address;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Data
public class MyAddressesPage extends RegistrationPage {
    public MyAddressesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h3")
    private List<WebElement> addressAliases;

    @FindBy(css = "div.col-sm-6")
    private List<WebElement> pageAddresses;

    @FindBy(css = "div.col-sm-6 li a[title = \"Update\"]")
    private List<WebElement> updateButtons;

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

    int verifyAddresses(User user) {

        String userAddresses = "";
        for (Address address : user.getAddresses()) {
            userAddresses += address + "\n";
        }

        String pageAddresses = "";
        for (WebElement w : this.pageAddresses) {
            pageAddresses += w.getText() + "\n";
            System.out.println(w.getText());
        }

        return userAddresses.compareTo(pageAddresses);
    }

    @FindBy(xpath = "//ul[@class='last_item item box']//span[contains(text(),'Update')]")
    private WebElement updateLastAddress;

    void updateAddress(User user) {
        MyAddressesUpdatePage maup = PageFactory.initElements(driver, MyAddressesUpdatePage.class);

        int userAddressesQuantity = user.getAddresses().size();
        if (userAddressesQuantity == updateButtons.size()) {
            for(int i = 0; i < updateButtons.size(); i++){
                updateButtons.get(i).click();
                addNewAddress(user.getAddresses().get(i));
                maup.saveUpdates();
            }
        }
    }
}
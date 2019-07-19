import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class Page {

    WebDriver driver;

    public Page(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@id='contact-link']//a")
    private WebElement contactUsButton;

    @FindBy(xpath = "//a[@class='account']")
    private WebElement myAccountButton;

    @FindBy(xpath = "//a[@class='logout']")
    private WebElement signOutButton;

    private boolean signInButtonIsDisplayed(){
        return signInButton.isDisplayed();
    }

    boolean accountWasNotRegistered(){
        return signInButtonIsDisplayed();
    }

    private boolean signOutButtonIsDisplayed(){
        return signOutButton.isDisplayed();
    }

    boolean accountWasRegistered(){
        return signOutButtonIsDisplayed();
    }

    void clickSignOutButton(){
        waitForWebElementToBeClickable(signOutButton);
        signOutButton.click();
    }

    void clickMyAccountButton(){
        waitForWebElementToBeClickable(myAccountButton);
        myAccountButton.click();
    }

    void openMyAccount(){
        clickMyAccountButton();
    }

    void clickSignInButton(){
        waitForWebElementToBeClickable(signInButton);
        signInButton.click();
    }

    void clickContactUsButton(){
        waitForWebElementToBeClickable(contactUsButton);
        contactUsButton.click();
    }

    void waitForWebElementVisibility(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    void waitForWebElementToBeClickable(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    void checkPageTitle(){}

    void waitPageIsLoaded(){
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }
}

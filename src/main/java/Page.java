import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@Getter
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
        waitForWebElementVisibility(signOutButton);
        return signOutButton.isDisplayed();
    }

    boolean accountWasRegistered(){
        return signOutButtonIsDisplayed();
    }

    void signOut(){
        click(signOutButton);
    }

    protected void click(WebElement webElement){
        webElement.click();
    }

    protected void clickAfterWaiting(WebElement webElement){
        waitForWebElementToBeClickable(webElement);
        click(webElement);
    }

    void openMyAccount(){
        clickAfterWaiting(myAccountButton);
    }

    void waitForWebElementVisibility(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    void waitForWebElementToBeClickable(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void select(WebElement webElement, String valueToSelect) {
        Select dropdown = new Select(webElement);
        dropdown.selectByValue(String.valueOf(valueToSelect));
    }

    protected void clear(WebElement webElement) {
        webElement.clear();
    }

    public String getDropBoxValueAttribute(WebElement dropBox) {
        return dropBox.getAttribute("value");
    }

    protected String getTextBoxValueAttribute(WebElement textBox) {
        return textBox.getAttribute("value");
    }

    protected void send(WebElement webElement, String textToSend) {
        waitForWebElementVisibility(webElement);
        webElement.clear();
        webElement.sendKeys(textToSend);
    }

    void waitPageIsLoaded(){
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }
}

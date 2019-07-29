import lombok.Getter;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class RegistrationPage extends Page {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//input[@id='id_gender1']")
    private WebElement male;

    @FindBy(xpath = ".//input[@id='id_gender2']")
    private WebElement female;

    @FindBy(xpath = "//*[@id='customer_firstname']")
    private WebElement сustomerFirstName;

    @FindBy(xpath = "//*[@id='customer_lastname']")
    private WebElement сustomerLastName;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement password;

    @FindBy(xpath = "//*[@id='days']")
    private WebElement days;

    @FindBy(xpath = "//*[@id='months']")
    private WebElement months;

    @FindBy(xpath = "//*[@id='years']")
    private WebElement years;

    @FindBy(xpath = "//input[@id='newsletter']")
    private WebElement newsletter;

    @FindBy(xpath = "//input[@id='optin']")
    private WebElement specialOffers;

    @FindBy(xpath = "//*[@id='company']")
    private WebElement company;

    @FindBy(xpath = "//*[@id='address1']")
    private WebElement address1;

    @FindBy(xpath = "//input[@id='firstname']")
    private WebElement firstname;

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement lastname;

    @FindBy(xpath = "//*[@id='address2']")
    private WebElement address2;

    @FindBy(xpath = "//*[@id='city']")
    private WebElement city;

    @FindBy(xpath = "//*[@id='id_state']")
    private WebElement state;

    @FindBy(xpath = "//*[@id='postcode']")
    private WebElement postcode;

    @FindBy(xpath = "//*[@id='id_country']")
    private WebElement country;

    @FindBy(xpath = "//*[@id='other']")
    private WebElement additionalInformation;

    @FindBy(xpath = "//*[@id='phone']")
    private WebElement homePhone;

    @FindBy(xpath = "//*[@id='phone_mobile']")
    private WebElement mobilePhone;

    @FindBy(xpath = "//*[@id='alias']")
    private WebElement alias;

    @FindBy(xpath = "//*[@id='submitAccount']/span")
    private WebElement register;

    @FindBy(xpath = "//*[@id='columns']/div[1]")
    private WebElement invalidData;

    private void chooseGender(User user) {
        clickAfterWaiting(user.getPersonalInfo().isGenderMale() ? male : female);
    }

    void chooseNewsLetter(User user) {
        while(user.getPersonalInfo().isNewsLetter() != newsletter.isSelected()){
            click(newsletter);
        }
    }

    void chooseSpecialOffers(User user) {
        while(user.getPersonalInfo().isSpecialOffers() != specialOffers.isSelected()){
            click(specialOffers);
        }
    }

    void createNewAccountWithOnlyRequiredFields(User user) {
        send(getСustomerFirstName(), user.getPersonalInfo().getCustomerFirstName());
        send(getСustomerLastName(), user.getPersonalInfo().getCustomerLastName());
        send(getPassword(), user.getPersonalInfo().getPassword());
        select(getDays(), user.getPersonalInfo().getDay());
        select(getMonths(), user.getPersonalInfo().getMonth());
        select(getYears(), user.getPersonalInfo().getYear());

        send(getFirstname(), user.getAddress().getFirstName());
        send(getLastname(), user.getAddress().getLastName());
        send(getAddress1(), user.getAddress().getAddress1());
        send(getCity(), user.getAddress().getCity());
        select(getState(),user.getAddress().getState());
        send(getPostcode(), user.getAddress().getPostcode());
        select(getCountry(),user.getAddress().getCountry());
        send(getMobilePhone(), user.getAddress().getMobilePhone());
        send(getAlias(), user.getAddress().getAlias());
}

    void createNewAccountWithAllFields(User user) {
        chooseGender(user);
        send(getСustomerFirstName(), user.getPersonalInfo().getCustomerFirstName());
        send(getСustomerLastName(), user.getPersonalInfo().getCustomerLastName());
        send(getPassword(), user.getPersonalInfo().getPassword());
        select(getDays(), user.getPersonalInfo().getDay());
        select(getMonths(), user.getPersonalInfo().getMonth());
        select(getYears(), user.getPersonalInfo().getYear());
        chooseNewsLetter(user);
        chooseSpecialOffers(user);

        send(getFirstname(), user.getAddress().getFirstName());
        send(getLastname(), user.getAddress().getLastName());
        send(getCompany(), user.getAddress().getCompany());
        send(getAddress1(), user.getAddress().getAddress1());
        send(getAddress2(), user.getAddress().getAddress2());
        send(getCity(), user.getAddress().getCity());
        select(getState(),user.getAddress().getState());
        send(getPostcode(), user.getAddress().getPostcode());
        select(getCountry(),user.getAddress().getCountry());
        send(getAdditionalInformation(), user.getAddress().getAdditionalInformation());
        send(getHomePhone(), user.getAddress().getHomePhone());
        send(getMobilePhone(), user.getAddress().getMobilePhone());
        send(getAlias(), user.getAddress().getAlias());
    }

    void registerAccount() {
        waitForWebElementVisibility(register);
        clickAfterWaiting(register);
    }
}
import lombok.Getter;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

@Getter
public class RegistrationPage extends Page {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//input[@id='id_gender1']")
    private WebElement male;

    protected boolean getMaleAttribute() {
        return male.isSelected();
    }

    @FindBy(xpath = ".//input[@id='id_gender2']")
    private WebElement female;

    protected String getFemaleAttribute() {
        return female.getAttribute("checked");
    }

    @FindBy(xpath = "//*[@id='customer_firstname']")
    private WebElement сustomerFirstName;

    @FindBy(xpath = "//*[@id='customer_lastname']")
    private WebElement сustomerLastName;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement email;

    protected String getEmailAttribute() {
        return email.getAttribute("value");
    }

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement password;

    protected String getPasswordAttribute() {
        return password.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='days']")
    private WebElement days;

    protected String getDaysAttribute() {
        return days.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='months']")
    private WebElement months;

    protected String getMonthsAttribute() {
        return months.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='years']")
    private WebElement years;

    protected String getYearsAttribute() {
        return years.getAttribute("value");
    }

    @FindBy(xpath = "//input[@id='newsletter']")
    private WebElement newsletter;

    protected boolean getNewsletterAttribute() {
        return newsletter.isSelected();//getAttribute("checked") == "true";
    }

    @FindBy(xpath = "//input[@id='optin']")
    private WebElement specialOffers;

    protected boolean getSpecialOffersAttribute() {
        return specialOffers.isSelected();//.getAttribute("checked") == "true";
    }

    @FindBy(xpath = "//*[@id='company']")
    private WebElement company;

    protected String getCompanyAttribute() {
        return company.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='address1']")
    private WebElement address1;

    protected String getAddress1Attribute() {
        return address1.getAttribute("value");
    }

    @FindBy(xpath = "//input[@id='firstname']")
    private WebElement firstname;

    protected String getFirstnameAttribute() {
        return firstname.getAttribute("value");
    }

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement lastname;

    protected String getLastnameAttribute() {
        return lastname.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='address2']")
    private WebElement address2;

    protected String getAddress2Attribute() {
        return address2.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='city']")
    private WebElement city;

    protected String getCityAttribute() {
        return city.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='id_state']")
    private WebElement state;

    protected String getStateAttribute() {
        return state.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='postcode']")
    private WebElement postcode;

    protected String getPostcodeAttribute() {
        return postcode.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='id_country']")
    private WebElement country;

    protected String getCountryAttribute() {
        return country.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='other']")
    private WebElement additionalInformation;

    protected String getAdditionalInformationAttribute() {
        return additionalInformation.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='phone']")
    private WebElement homePhone;

    protected String getHomePhoneAttribute() {
        return homePhone.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='phone_mobile']")
    private WebElement mobilePhone;

    protected String getMobilePhoneAttribute() {
        return mobilePhone.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='alias']")
    private WebElement alias;

    protected String getAliasAttribute() {
        return alias.getAttribute("value");
    }

    @FindBy(xpath = "//*[@id='submitAccount']/span")
    private WebElement register;

    @FindBy(xpath = "//*[@id='columns']/div[1]")
    private WebElement invalidData;

    private void chooseGender(User user) {
        clickAfterWaiting(user.isGenderMale() ? male : female);
    }

    protected void chooseNewsLetter(User user) {
        while(user.isNewsLetter() != getNewsletterAttribute()){
            click(newsletter);
        }
    }

    protected void chooseSpecialOffers(User user) {
        while(user.isSpecialOffers() != getSpecialOffersAttribute()){
            click(specialOffers);
        }
    }

    void createNewAccountWithOnlyRequiredFields(User user) {
        send(getСustomerFirstName(), user.getFirstName());
        send(getСustomerLastName(), user.getLastName());
        send(getPassword(), user.getPassword());
        select(getDays(), user.getDate());
        select(getMonths(), user.getMonth());
        select(getYears(), user.getYear());
        send(getAddress1(), user.getAddress1());
        send(getCity(), user.getCity());
        select(getState(),user.getState());
        send(getPostcode(), user.getPostcode());
        select(getCountry(),user.getCountry());
        send(getMobilePhone(), user.getMobilePhone());
        send(getAlias(), user.getAlias());
}

    void createNewAccountWithAllFields(User user) {
        chooseGender(user);
        send(getСustomerFirstName(), user.getFirstName());
        send(getСustomerLastName(), user.getLastName());
        send(getPassword(), user.getPassword());
        select(getDays(), user.getDate());
        select(getMonths(), user.getMonth());
        select(getYears(), user.getYear());
        chooseNewsLetter(user);
        chooseSpecialOffers(user);
        send(getCompany(), user.getCompany());
        send(getAddress1(), user.getAddress1());
        send(getAddress2(), user.getAddress2());
        send(getCity(), user.getCity());
        select(getState(),user.getState());
        send(getPostcode(), user.getPostcode());
        select(getCountry(),user.getCountry());
        send(getAdditionalInformation(), user.getAdditionalInformation());
        send(getHomePhone(), user.getHomePhone());
        send(getMobilePhone(), user.getMobilePhone());
        send(getAlias(), user.getAlias());
    }

    public void registerAccount() {
        waitForWebElementVisibility(register);
        clickAfterWaiting(register);
    }
}
import lombok.Getter;
import models.Address;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private ArrayList<String> baseErrors = new ArrayList<>();

    private ArrayList<WebElement> pageElements = new ArrayList<>();

    @FindBy(css = "div.alert li")
    List<WebElement> userErrors;

    private void chooseGender(User user) {
        clickAfterWaiting(user.getPersonalInfo().isGenderMale() ? male : female);
    }

    void createNewAccount(User user) {
        fillAllPersonalInfo(user);
        fillAllAddresses(user);
    }

    void fillAllPersonalInfo(User user) {
        chooseGender(user);
        send(getСustomerFirstName(), user.getPersonalInfo().getCustomerFirstName());
        send(getСustomerLastName(), user.getPersonalInfo().getCustomerLastName());
        send(getPassword(), user.getPersonalInfo().getPassword());
        selectByValue(getDays(), user.getPersonalInfo().getDay());
        selectByValue(getMonths(), user.getPersonalInfo().getMonth());
        selectByValue(getYears(), user.getPersonalInfo().getYear());
        chooseCheckBox(getNewsletter(), user);
        chooseCheckBox(getSpecialOffers(), user);
    }

    void fillAllAddresses(User user) {
        ArrayList<Address> addresses = user.getAddresses();
        if (addresses.size() > 0) {
            addNewAddress(addresses.get(0));
            this.registerAccount();

            if (addresses.size() > 1) {
                MyAccountPage myAccountPage =
                        PageFactory.initElements(driver, MyAccountPage.class);
                myAccountPage.openMyAddresses();
                MyAddressesPage myAddressesPage =
                        PageFactory.initElements(driver, MyAddressesPage.class);
                List<WebElement> aliases = myAddressesPage.getAddressAliases();
                MyAddressesAddPage myAddressesAddPage =
                        PageFactory.initElements(driver, MyAddressesAddPage.class);

                for (Address address : addresses) {
                    if (!aliasIsOnThePage(address.getAlias(), aliases)) {
                        myAddressesPage.addANewAddress();
                        myAddressesAddPage.addNewAddress(address);
                        myAddressesAddPage.saveUpdates();
                    }
                }
            }
        }
    }

    private boolean aliasIsOnThePage(String alias, List<WebElement> webElements) {
        for (WebElement web : webElements) {
            if (alias.toUpperCase().compareTo(web.getText()) == 0) {
                return true;
            }
        }
        return false;
    }

    void addNewAddress(Address address) {
        send(firstname, address.getFirstName());
        send(lastname, address.getLastName());
        send(company, address.getCompany());
        send(address1, address.getAddress1());
        send(address2, address.getAddress2());
        send(city, address.getCity());
        selectByVisibleText(state, address.getState());
        selectByVisibleText(country, address.getCountry());
        send(postcode, address.getPostcode());
        send(homePhone, address.getHomePhone());
        send(mobilePhone, address.getMobilePhone());
        send(additionalInformation, address.getAdditionalInformation());
        send(alias, address.getAlias());
    }

    void registerAccount() {
        waitForWebElementVisibility(register);
        clickAfterWaiting(register);
    }

    @Getter
    enum Errors {
        FIRSTNAME_OR_CUSTOMER_FIRSTNAME_TOO_LONG("firstname is too long. Maximum length: 32", "firstname"),
        FIRSTNAME_OR_CUSTOMER_FIRSTNAME_IS_INVALID("firstname is invalid.", "firstname"),
        FIRSTNAME_IS_REQUIRED("firstname is required.", "firstname");

        String errorText;
        String field;

        Errors(String errorText, String field) {
            this.errorText = errorText;
            this.field = field;
        }
    }

    public void fillPageElementsList() throws IllegalAccessException {

        waitForWebElementVisibility(male);
        List<Field> allElements = new ArrayList<>(Arrays.asList(RegistrationPage.class.getDeclaredFields()));
        for (Field allElement : allElements) {
            if (allElement.getType().toString().equals("interface org.openqa.selenium.WebElement")) {

                RegistrationPage obj = PageFactory.initElements(super.driver, RegistrationPage.class);
                pageElements.add(((WebElement) allElement.get(obj)));
            }
        }
    }

    public void fillErrorArrayList() {
        for (Errors a : Errors.values()) {
            baseErrors.add(a.getErrorText());
        }
    }

    String findTooLong(){
        return findError();
    }

    String findRequired(){
        return findError();
    }

    String findInvalid(){
        return findError();
    }

    String findError() {
        fillErrorArrayList();
        String userErr = "";
        for (WebElement webElement : userErrors) {
            if (baseErrors.contains(webElement.getText())) {
                for (Errors a : Errors.values()) {
                    if(a.getErrorText().compareTo(webElement.getText()) == 0){
                        userErr+=a.getField()+"\n";
                    }
                }
            }
        }
        return userErr;
    }

    boolean pageElementsAreVisibleAndDisplayed() throws IllegalAccessException {
        fillPageElementsList();
        for (WebElement webElement : pageElements) {
            if (!getIsDisplayed(webElement) && !getIsEnabled(webElement)) {
                //System.out.println("Page element \"" + webElement + "\" isn't shown");
                LOGGER.error("Page element \"" + webElement + "\" isn't shown");
                return false;
            }
        }
        LOGGER.error("All page elements are shown");
        return true;
    }
}

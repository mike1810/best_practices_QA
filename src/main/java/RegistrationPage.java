import lombok.Getter;
import models.Address;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
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

    private void chooseGender(User user) {
        clickAfterWaiting(user.getPersonalInfo().isGenderMale() ? male : female);
    }

    void createNewAccountWithAllFields(User user) {
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

                for(Address address: addresses){
                    if(!aliasIsOnThePage(address.getAlias(), aliases)){
                        myAddressesPage.addANewAddress();
                        myAddressesAddPage.addNewAddress(address);
                        myAddressesAddPage.saveUpdates();
                    }
                }
            }
        }
        this.openMyAccount();
    }

    private boolean aliasIsOnThePage(String alias, List<WebElement> webElements){
        for(WebElement web : webElements){
            if(alias.toUpperCase().compareTo(web.getText()) == 0){
                return true;
            }
        }
        return false;
    }

    void addNewAddress(Address address) {
        send(getFirstname(), address.getFirstName());
        send(getLastname(), address.getLastName());
        send(getCompany(), address.getCompany());
        send(getAddress1(), address.getAddress1());
        send(getAddress2(), address.getAddress2());
        send(getCity(), address.getCity());
        selectByVisibleText(getState(), address.getState());
        selectByVisibleText(country, address.getCountry());
        send(getPostcode(), address.getPostcode());
        send(getHomePhone(), address.getHomePhone());
        send(getMobilePhone(), address.getMobilePhone());
        send(getAdditionalInformation(), address.getAdditionalInformation());
        send(getAlias(), address.getAlias());
    }

    void registerAccount() {
        waitForWebElementVisibility(register);
        clickAfterWaiting(register);
    }


    final static String MESSAGE_ERROR_FIRSTNAME = "firstname is too long. Maximum length: 32";
    final static String MESSAGE_ERROR_LASTNAME = "lastname is too long. Maximum length: 32";
    final static String MESSAGE_ERROR_EMAIL = "email is invalid.";
    final static String MESSAGE_ERROR_PASSWORD = "passwd is invalid.";
    final static String MESSAGE_ERROR_PASSWORD2 = "passwd is required.";

    final static String MESSAGE_ERROR_ADDRESS1 = "address1 is too long. Maximum length: 128";
    final static String MESSAGE_ERROR_ZIP = "The Zip/Postal code you've entered is invalid. It must follow this format: 00000";
    final static String MESSAGE_ERROR_STATE = "This country requires you to choose a State.";
    final static String MESSAGE_ERROR_COUNTRY = "Country is invalid";
    final static String MESSAGE_ERROR_CITY = "city is too long. Maximum length: 64";
    final static String MESSAGE_ERROR_MOBILE = "phone_mobile is invalid.";

    private ArrayList<String> baseErrors = new ArrayList<>();

    private ArrayList<WebElement> pageElements = new ArrayList<>();

    @FindBy(css = "div.alert li")
    List<WebElement> userErrors;

    public void fillPageElementsList(){
        /*pageElements.add(male);
        pageElements.add(female);
        pageElements.add(customerFirstName);
        pageElements.add(customerLastName);
        pageElements.add(email);
        pageElements.add(password);
        pageElements.add(day);
        pageElements.add(month);
        pageElements.add(year);
        pageElements.add(newsletter);
        pageElements.add(specialOffers);
        pageElements.add(firstNameInAdressForm);
        pageElements.add(lastNameInAdressForm);
        pageElements.add(company);
        pageElements.add(adressLine1);
        pageElements.add(adressLine2);
        pageElements.add(city);
        pageElements.add(state);
        pageElements.add(zip);
        pageElements.add(country);
        pageElements.add(additionalInformation);
        pageElements.add(homePhone);
        pageElements.add(mobilePhone);
        pageElements.add(anAdressAlias);
        pageElements.add(register);*/
    }

    public void fillErrorArrayList(){
        baseErrors.add(MESSAGE_ERROR_FIRSTNAME);
        baseErrors.add(MESSAGE_ERROR_LASTNAME);

        baseErrors.add(MESSAGE_ERROR_EMAIL);
        baseErrors.add(MESSAGE_ERROR_PASSWORD);
        baseErrors.add(MESSAGE_ERROR_PASSWORD2);

        baseErrors.add(MESSAGE_ERROR_ADDRESS1);
        baseErrors.add(MESSAGE_ERROR_CITY);
        baseErrors.add(MESSAGE_ERROR_STATE);
        baseErrors.add(MESSAGE_ERROR_ZIP);
        baseErrors.add(MESSAGE_ERROR_COUNTRY);
        baseErrors.add(MESSAGE_ERROR_MOBILE);
    }
    public boolean findError() {

        fillErrorArrayList();

        for (WebElement webElement : userErrors) {
            if (!baseErrors.contains(webElement.getText())) {
                //LOGGER.error("Error message: \"" + webElement.getText() + "\" isn't exist");
                return false;
            }
        }
        //LOGGER.info("All error messages are exist");
        return true;
    }
}

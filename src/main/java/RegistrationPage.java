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

    void createNewAccountWithOnlyRequiredFields(User user) {
        fillRequiredPersonalInfo(user);
        fillRequiredAddress(user);
    }

    void fillRequiredAddress(User user) {
        send(getFirstname(), user.getMainAddress().getFirstName());
        send(getLastname(), user.getMainAddress().getLastName());
        send(getAddress1(), user.getMainAddress().getAddress1());
        send(getCity(), user.getMainAddress().getCity());
        select(getState(), user.getMainAddress().getState());
        send(getPostcode(), user.getMainAddress().getPostcode());
        select(getCountry(), user.getMainAddress().getCountry());
        send(getMobilePhone(), user.getMainAddress().getMobilePhone());
        send(getAlias(), user.getMainAddress().getAlias());
    }

    void fillRequiredPersonalInfo(User user) {
        send(getСustomerFirstName(), user.getPersonalInfo().getCustomerFirstName());
        send(getСustomerLastName(), user.getPersonalInfo().getCustomerLastName());
        send(getPassword(), user.getPersonalInfo().getPassword());
        select(getDays(), user.getPersonalInfo().getDay());
        select(getMonths(), user.getPersonalInfo().getMonth());
        select(getYears(), user.getPersonalInfo().getYear());
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
        select(getDays(), user.getPersonalInfo().getDay());
        select(getMonths(), user.getPersonalInfo().getMonth());
        select(getYears(), user.getPersonalInfo().getYear());
        chooseCheckBox(getNewsletter(), user);
        chooseCheckBox(getSpecialOffers(), user);
    }

    void fillAllAddresses(User user) {
        ArrayList<Address> addresses = user.getAddresses();
        if (addresses.size() > 0) {
            addNewAddress(addresses.get(0));
            this.registerAccount();

            if (addresses.size() > 1) {
                MyAccountPage myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
                myAccountPage.openMyAddresses();
                MyAddressesPage myAddressesPage = PageFactory.initElements(driver, MyAddressesPage.class);
                List<WebElement> aliases = myAddressesPage.getAddressAliases();
                MyAddressesAddPage myAddressesAddPage = PageFactory.initElements(driver, MyAddressesAddPage.class);

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
        select(getState(), address.getState());
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


    private enum registrationErrors {

    }

}

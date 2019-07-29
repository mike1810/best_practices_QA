package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {


    private Address address;
    private PersonalInfo personalInfo;


    /*private boolean genderMale;
    private String customerFirstName;
    private String customerLastName;
    @JsonIgnore
    private String email;
    private String password;
    private String date;
    private String month;
    private String year;
    private boolean newsLetter;
    private boolean specialOffers;

    private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postcode;
    private String country;
    private String additionalInformation;
    private String homePhone;
    private String mobilePhone;
    private String alias;*/


/*
    private void setNegative() {
        this.genderMale = true;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.date = "-1";
        this.month = "-1";
        this.year = "-1";
        this.newsLetter = true;
        this.specialOffers = true;
        this.company = "";
        this.address1 = "";
        this.address2 = "";
        this.city = "0";
        this.state = "0";
        this.postcode = "0";
        this.country = "0";
        this.additionalInformation = "";
        this.homePhone = "";
        this.mobilePhone = "";
        this.alias = "";
    }

    @DataProvider(name = "DPForAccountCreating")
    public Object[] dataProviderNewUser() {
        return new Object[]{
                new models.User()
        };
    }

    @DataProvider(name = "DPForAccountCreatingNegative")
    public Object[] dataProviderNewUserNegative() {
        models.User user = new models.User();
        user.setNegative();
        return new Object[]{
                user
        };
    }*/

    @Override
    public String toString(){
        return personalInfo.getCustomerFirstName() + " " + personalInfo.getCustomerLastName();
    }
}

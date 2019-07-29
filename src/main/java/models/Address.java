package models;

import lombok.Data;
import lombok.ToString;

@Data
public class Address {
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
    private String alias;

    @Override
    public String toString(){
        return  "firstName: " + firstName +
                "; lastName: " + lastName +
                "; company: " + company +
                "; address1: " + address1 +
                "; address2: " + address2 +
                "; city: " + city +
                "; state: " + state +
                "; postcode: " + postcode +
                "; country: " + country +
                "; additionalInformation: " + additionalInformation +
                "; homePhone: " + homePhone +
                "; mobilePhone: " + mobilePhone +
                "; alias: " + alias;
    }

    public int compareTo(Address comparedWith){
        return this.toString().compareTo(comparedWith.toString());
    }
}
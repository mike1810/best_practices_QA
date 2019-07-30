package models;

import lombok.Data;

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
        return  alias.toUpperCase() + "\n"
                + firstName + " " + lastName + "\n"
                + company + "\n"
                + address1 + " " + address2 + "\n"
                + city + ", " + state + " " + postcode + "\n"
                + country + "\n"
                + homePhone + "\n"
                + mobilePhone + "\n"
                + "Update" + "\n"
                + "Delete";
    }

    public int compareTo(Address comparedWith){
        return this.toString().compareTo(comparedWith.toString());
    }
}
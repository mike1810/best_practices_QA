package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Address mainAddress;
    private PersonalInfo personalInfo;

    private ArrayList<Address> addresses;

    @Override
    public String toString() {
        return personalInfo.getCustomerFirstName() + " " + personalInfo.getCustomerLastName();
    }

    public String fieldsWillBeNegative(User user) {

        String negativeFields = "";
        negativeFields += (personalInfo.isGenderMale() != user.getPersonalInfo().isGenderMale()) ? "genderMale\n" : "";

        negativeFields += (personalInfo.getCustomerFirstName().compareTo
                (user.getPersonalInfo().getCustomerFirstName()) == 0) ?
                (
                        (negativeFields.contains("firstname")) ? "" : "firstname\n"
                ) :
                "";

        negativeFields += (personalInfo.getCustomerLastName().compareTo
                (user.getPersonalInfo().getCustomerLastName()) == 0) ? "" :
                (
                        (negativeFields.contains("lastname")) ? "" : "lastname\n"
                );

        /*negativeFields += (personalInfo.getEmail().compareTo
                (user.getPersonalInfo().getEmail()) == 0) ? "" : "email\n";*/

        negativeFields += (personalInfo.getDay().compareTo
                (user.getPersonalInfo().getDay()) == 0) ? "" : "day\n";

        negativeFields += (personalInfo.getMonth().compareTo
                (user.getPersonalInfo().getMonth()) == 0) ? "" : "month\n";

        negativeFields += (personalInfo.getYear().compareTo
                (user.getPersonalInfo().getYear()) == 0) ? "" : "year\n";

        negativeFields += (personalInfo.getPassword().compareTo
                (user.getPersonalInfo().getPassword()) == 0) ? "" : "password\n";

        negativeFields += (personalInfo.isNewsLetter() != user.getPersonalInfo().isNewsLetter()) ? "newsLetter\n" : "";

        negativeFields += (personalInfo.isSpecialOffers() != user.getPersonalInfo().isSpecialOffers()) ? "specialOffers\n" : "";

        if (this.getAddresses().size() == user.getAddresses().size()) {
            int size = user.getAddresses().size();
            for (int i = 0; i < size; i++) {
                negativeFields += (addresses.get(i).getFirstName().compareTo
                        (user.getAddresses().get(i).getFirstName()) == 0) ? "" :
                        (
                                (negativeFields.contains("firstname")) ? "" : "firstname\n"
                        );
                negativeFields += (addresses.get(i).getLastName().compareTo
                        (user.getAddresses().get(i).getLastName()) == 0) ? "" : (
                                (negativeFields.contains("lastname")) ? "" : "lastname\n"
                        );
                negativeFields += (addresses.get(i).getCompany().compareTo
                        (user.getAddresses().get(i).getCompany()) == 0) ? "" : "company\n";
                negativeFields += (addresses.get(i).getAddress1().compareTo
                        (user.getAddresses().get(i).getAddress1()) == 0) ? "" : "address1\n";
                negativeFields += (addresses.get(i).getAddress2().compareTo
                        (user.getAddresses().get(i).getAddress2()) == 0) ? "" : "address2\n";
                negativeFields += (addresses.get(i).getCity().compareTo
                        (user.getAddresses().get(i).getCity()) == 0) ? "" : "city\n";
                negativeFields += (addresses.get(i).getState().compareTo
                        (user.getAddresses().get(i).getState()) == 0) ? "" : "state\n";
                negativeFields += (addresses.get(i).getPostcode().compareTo
                        (user.getAddresses().get(i).getPostcode()) == 0) ? "" : "postcode\n";
                negativeFields += (addresses.get(i).getCountry().compareTo
                        (user.getAddresses().get(i).getCountry()) == 0) ? "" : "country\n";
                negativeFields += (addresses.get(i).getAdditionalInformation().compareTo
                        (user.getAddresses().get(i).getAdditionalInformation()) == 0) ? "" : "additionalInformation\n";
                negativeFields += (addresses.get(i).getHomePhone().compareTo
                        (user.getAddresses().get(i).getHomePhone()) == 0) ? "" : "homePhone\n";
                negativeFields += (addresses.get(i).getMobilePhone().compareTo
                        (user.getAddresses().get(i).getMobilePhone()) == 0) ? "" : "mobilePhone\n";
                negativeFields += (addresses.get(i).getAlias().compareTo
                        (user.getAddresses().get(i).getAlias()) == 0) ? "" : "alias\n";
            }
        }

        return negativeFields;

    }
}
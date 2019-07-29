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

    @Override
    public String toString(){
        return personalInfo.getCustomerFirstName() + " " + personalInfo.getCustomerLastName();
    }
}
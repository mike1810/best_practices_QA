package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Email email;
    private Password password;
}

enum AccountIs{
    REGISTERED("Registered"),NOT_REGISTERED("Not registered");
    String key;
    AccountIs(String key){
        this.key = key;
    }
}
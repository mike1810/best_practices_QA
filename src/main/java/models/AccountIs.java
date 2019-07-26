package models;

import lombok.Getter;

@Getter
public enum AccountIs{
    REGISTERED,
    NOT_REGISTERED
    /*REGISTERED("Registered"),NOT_REGISTERED("Not registered");
    private String key;
    AccountIs(String key){
        this.key = key;
    }*/
}
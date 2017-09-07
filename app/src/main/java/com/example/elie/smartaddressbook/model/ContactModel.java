package com.example.elie.smartaddressbook.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by elie on 17-8-5.
 */

public class ContactModel implements Serializable {

    private UUID ID;
    private String firstname;
    private String lastname;
    private char sex;
    private String address;
    private String phoneNumber;



    public ContactModel(){
        ID = UUID.randomUUID();
    }



    public ContactModel(String firstname, String lastname, char sex, String address, String phoneNumber) {

        ID = UUID.randomUUID();
        this.firstname = firstname;
        this.lastname = lastname;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactModel that = (ContactModel) o;

        if (sex != that.sex) return false;
        if (!firstname.equals(that.firstname)) return false;
        if (!lastname.equals(that.lastname)) return false;
        if (!address.equals(that.address)) return false;
        return phoneNumber.equals(that.phoneNumber);

    }

    @Override
    public int hashCode() {
        int result = firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + (int) sex;
        result = 31 * result + address.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        return result;
    }
}

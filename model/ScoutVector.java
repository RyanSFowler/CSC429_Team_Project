/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Vector;
import javafx.beans.property.SimpleStringProperty;


public class ScoutVector {

private final SimpleStringProperty firstName;
private final SimpleStringProperty lastName;
private final SimpleStringProperty middleInitial;
private final SimpleStringProperty dateOfBirth;
private final SimpleStringProperty phoneNumber;
private final SimpleStringProperty email;

 public ScoutVector(Vector<String> book)
    {
	firstName =  new SimpleStringProperty(book.elementAt(0));
	lastName =  new SimpleStringProperty(book.elementAt(1));
  middleInitial =  new SimpleStringProperty(book.elementAt(2));
	dateOfBirth =  new SimpleStringProperty(book.elementAt(3));
  phoneNumber =  new SimpleStringProperty(book.elementAt(4));
  email =  new SimpleStringProperty(book.elementAt(5));
    }

    public void setFirstName(String FirstName) {
        firstName.set(FirstName);
    }

    public void setLastName(String LastName) {
        lastName.set(LastName);
    }

    public void setMiddleInitial(String MiddleInitial) {
        middleInitial.set(MiddleInitial);
    }

    public void setDateOfBirth(String DateOfBirth) {
        dateOfBirth.set(DateOfBirth);
    }

    public void setPhoneNumber(String PhoneNumber) {
        phoneNumber.set(PhoneNumber);
    }

    public void setEmail(String Email) {
        email.set(Email);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getMiddleInitial() {
        return middleInitial.get();
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public String getEmail() {
        return email.get();
    }
}

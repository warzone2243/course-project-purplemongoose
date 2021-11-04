package entity.profiles;

import entity.datafile.Email;
import entity.datafile.Name;
import entity.datafile.Phone;

import java.io.Serializable;

public class Person implements ProfileType, Serializable {

    /** Stores all values as subclasses
     * Getters return all values as Strings
     */

    private final Name name;
    private final Phone phone;
    private final Email email;
    private final String username;


    public Person(Name name, Phone phone, Email email, String username) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
    }

    @Override
    public String getName() {
        return name.getFullName();
    }

    public String getPronouns() {
        return name.getPronouns();
    }

    @Override
    public String getPhone() {
        return phone.getPhone();
    }

    @Override
    public String getEmail() {
        return email.getEmail();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

}

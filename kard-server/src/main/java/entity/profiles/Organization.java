package entity.profiles;

import entity.datafiles.Email;
import entity.datafiles.Phone;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Organization implements ProfileType, Serializable {

    /**
     * Stores the profile information related to this Organization
     */
    @Serial
    private static final long serialVersionUID = 8267757690652968509L;
    private String orgName;
    private Phone orgPhone;
    private Email orgEmail;
    private String orgUsername;

    // the setters

    /**
     * Organization starts off not having any clients
     */
    public Organization(String orgName, Phone phone, Email email, String orgUsername) {
        this.orgName = orgName;
        this.orgPhone = phone;
        this.orgEmail = email;
        this.orgUsername = orgUsername;
    }

    // the getters

    /**
     * Gets the name of the organization
     * @return string of the name of the organization
     */
    @Override
    public String getName() {
        return orgName;
    }

    /**
     * Gets a string representation of the phone number of the organization
     *
     * Use getter method to retrieve string representation of phone number
     * otherwise stored as class type Phone
     *
     * @return string for the phone number
     */
    @Override
    public String getPhone() {
        return orgPhone.getPhone();
    }

    /**
     * Gets a string representation of the email address
     *
     * Use getter method to retrieve string representation of email
     * otherwise stored as class type Email
     *
     * @return string for the email address
     */
    @Override
    public String getEmail() {
        return orgEmail.getEmail();
    }

    /**
     * Gets a string representation for the username of this account
     * @return string for the username
     */
    @Override
    public String getUsername() {
        return orgUsername;
    }

    @Override
    public boolean equals(Object otherOrg) {
        if (this == otherOrg) return true;
        if (otherOrg == null || getClass() != otherOrg.getClass()) return false;
        Organization that = (Organization) otherOrg;
        return Objects.equals(orgName, that.orgName) && Objects.equals(orgPhone, that.orgPhone) && Objects.equals(orgEmail, that.orgEmail) && Objects.equals(orgUsername, that.orgUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgName, orgPhone, orgEmail, orgUsername);
    }

    public OrganizationMemento createMemento(){
        return new OrganizationMemento(this.orgName, this.orgPhone, this.orgEmail, this.orgUsername);
    }

    public void restore(OrganizationMemento memento){
        if (memento != null){
            this.orgName = memento.getName();
            this.orgPhone = new Phone(memento.getPhone());
            this.orgEmail = new Email(memento.getEmail());
            this.orgUsername = memento.getUsername();
        }
    }
}

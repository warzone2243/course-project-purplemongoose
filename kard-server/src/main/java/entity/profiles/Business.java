package entity.profiles;

import entity.accounts.PersonalAccount;
import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;

import java.io.Serial;
import java.io.Serializable;

/** Represents a Business, which is a special type of Organization.
 *
 * Businesses have a singular Owner.
 *
 */
public class Business extends Organization implements Serializable, ProfileType {
    @Serial
    private static final long serialVersionUID = 6906529685826775709L;
    private String companyName;
    private Phone companyPhone;
    private Email companyEmail;
    private final Person owner;

    // The setters

    public Business(String companyName, Phone companyPhone, Email companyEmail, String companyUsername,
                    Person owner) {
        super(companyName, companyPhone, companyEmail, companyUsername);
        this.owner = owner;
    }

    // The getters

    public String getOwnerName() {
        return owner.getName();
    }
}

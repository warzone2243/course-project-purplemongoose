package entity;

import java.io.Serializable;
import java.util.UUID;

/**
 * An interface for expressing the necessary details to store an item in a database.
 */
public interface Storable {
    /**Returns the UUID corresponding to the object.
     *
     * @return The uuid corresponding to the object.
     */
    UUID getId();
}

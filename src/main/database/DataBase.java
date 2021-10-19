package database;

import entity.Storable;

import java.util.UUID;


/**
 * An abstract class meant for representing the basic functionality of a database.
 *
 * @param <T> Class for which the database stores.
 */
public abstract class DataBase<T extends Storable> {

    /**Perform a lookup to receive the corresponding to the id given on the database.
     *
     * @param id The id of the object to be returned.
     * @return The object corresponding to the lookup.
     */
    public abstract T query(UUID id);

    /**Add a new entry to the database.
     *
     * @param entry The object to add.
     * @return The UUID generated for the entry into the database.
     */
    public abstract UUID add(T entry);

    /**Remove an entry from the database.
     *
     * @param id The id to remove.
     * @return whether the item was successfully removed.
     */
    public abstract boolean remove(UUID id);

    /**Return whether the object represented by the id is in the database.
     *
     * @param id The id to check.
     * @return whether the entry is in the database.
     */
    public abstract boolean contains(UUID id);

    /**Return whether the object entry is in the database.
     *
     * @param entry The entry to check.
     * @return whether the entry is in the database.
     */
    public boolean contains(T entry) {
        return contains(entry.getId());
    };

}

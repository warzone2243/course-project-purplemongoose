package database;

import java.util.UUID;


/**
 * An abstract class meant for representing the basic functionality of a table within a database.
 *
 * @param <T> Class for which the table stores.
 */
public abstract class DataBase<T extends Storable> {
    public long table_no;

    /**Perform a lookup to receive the corresponding entry to the id given in the table.
     *
     * @param id The id of the object to be returned.
     * @return The object corresponding to the lookup.
     */
    public abstract T query(UUID id);

    /**Add a new entry to the table.
     *
     * @param entry The object to add.
     * @return Whether the item was successfully added to the table.
     */
    public abstract boolean add(T entry);

    /**Remove an entry from the table.
     *
     * @param id The id to remove.
     * @return Whether the item was successfully removed.
     */
    public abstract boolean remove(UUID id);

    /**Return whether the object represented by the id is in the table.
     *
     * @param id The id to check.
     * @return whether the entry is in the database.
     */
    public abstract boolean contains(UUID id);
}

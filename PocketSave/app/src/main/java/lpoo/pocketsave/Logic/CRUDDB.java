package lpoo.pocketsave.Logic;

public interface CRUDDB<T> {

    /**
     * Add a new element of type T
     * @param element Element to be added
     * @return Returns true if it was added and false it not
     */
    boolean add(T element);

    /**
     * Update an element of type T
     * @param element Element to be updated
     * @return Returns true if it was updated and false it not
     */
    boolean update(T element);

    /**
     * Delete an element of type T with his id
     * @param id Id of the element to be deleted
     * @return Returns true if it was deleted and false it not
     */
    boolean delete(String id);
}

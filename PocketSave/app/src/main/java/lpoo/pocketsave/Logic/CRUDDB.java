package lpoo.pocketsave.Logic;

public interface CRUDDB<T> {

    public boolean add(T element);
    public boolean update(T element);
    public boolean delete(String id);
}

package lpoo.pocketsave.Logic;

/**
 * Created by luisnmartins on 14/05/2017.
 */

public class Type {

    //int id;
    //String title;

    /**
     * Add a new type if it doesn't exist and get his db id, even if already exists
     * @param title Name of the new type
     * @return Returns the db id of the type called title
     */
    /*public Type(String title){

        //try to add type to the DB
        id = DatabaseSingleton.getInstance().getDB().addType(title);
        //if type == -1 means that it already exists
        if(id == -1)
            id = DatabaseSingleton.getInstance().getDB().getTypeID(title); // get type id
        this.title = title;

    }

    public String getTitle(){
        return title;
    }

    public int getId(){
        return id;
    }*/
}

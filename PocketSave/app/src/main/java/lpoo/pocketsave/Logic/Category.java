package lpoo.pocketsave.Logic;

import java.util.Objects;

public class Category {

    private long id;
    private String title;
    private long typeID;
    private boolean mainMenu;
    private int color;

    /**
     * Create a new Category
     * @param title category's title
     * @param typeID Id of the category's type. From user's types' hashmap
     */
    public Category(String title, long typeID, boolean mainMenu, int color){

        this.typeID = typeID;
        this.title = title;
        this.mainMenu=mainMenu;
        this.color = color;

    }


    public int getColor(){
        return this.color;
    }

    /**
     *
     * @return
     */
    public long getID(){

        return id;
    }



    public String getTitle() {
        return title;
    }

    public long getTypeID() {
        return typeID;
    }


    /**
     * Set user's ID
     * @param id new user's id
     */
    public void setID(long id){
        this.id = id;
    }



    public boolean isMainMenu() {
        return mainMenu;
    }


    @Override
    public String toString() {
        return this.getTitle();
    }


    @Override
    public boolean equals(Object obj)
    {
        return this.getID() == ((Category)obj).getID();
    }


}
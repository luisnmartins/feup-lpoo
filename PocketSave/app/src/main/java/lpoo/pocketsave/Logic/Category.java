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

    /**
     * Gets the Category color
     * @return Returns the color
     */
    public int getColor(){
        return this.color;
    }

    public void setColor(int color){
        this.color = color;
    }

    /**
     * Gets the category ID
     * @return Returns the id
     */
    public long getID(){

        return id;
    }


    /**
     * Gets the Category Title
     * @return Returns the title
     */
    public String getTitle() {
        return title;
    }


    /**
     * Get the Type ID
     * @return Returns the id
     */
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


    /**
     * Get if the category should be shown in the main Menu
     * @return Returns true if it is to
     */
    public boolean isMainMenu() {
        return mainMenu;
    }


    /**
     * Set if the category is to be displayed in the main Menu
     * @param value True if is to display and false if not
     */
    public void setMainMenu(boolean value){
        this.mainMenu = value;
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
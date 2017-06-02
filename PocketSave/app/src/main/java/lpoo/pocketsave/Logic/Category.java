package lpoo.pocketsave.Logic;

public class Category {

    private long id;
    private long userID;
    private String title;
    private long typeID;
    private boolean mainMenu;
    private long iconID;
    private double estimatedValue;
    private double totalSpent;

    /**
     * Create a new Category
     * @param title category's title
     * @param typeID Id of the category's type. From user's types' hashmap
     */
    public Category(long id, String title, long typeID, boolean mainMenu){

        this.id = id;
        this.typeID = typeID;
        this.title = title;
        this.totalSpent=0;
        this.mainMenu=mainMenu;

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

    public void setEstimatedValue(double value){
        this.estimatedValue = value;
    }

    public boolean isMainMenu() {
        return mainMenu;
    }


    @Override
    public String toString() {
        return this.getTitle();
    }



}
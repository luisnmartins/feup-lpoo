package lpoo.pocketsave.Logic;


import java.util.HashMap;

public class User {

    private String username;
    private int totalSaved;
    private int availableMonth;
    private HashMap<String, Category> userCategories;
    static private User instance = null;

    static public User getInstance(){
        if(instance == null) {
            instance = new User();
        }
        return instance;

    }
    public User(){

        userCategories = new HashMap<String, Category>();
    };

    public User(String username, int totalSaved, HashMap<String, Category> oldCategories){

        this.username = username;
        this.totalSaved = totalSaved;
        this.userCategories = oldCategories;

    }

    public int getCategoryID(String title){

       return userCategories.get(title).getID();
    }


    public Category addCategory(String title, String type){


        Category newCategory;
       if(!userCategories.isEmpty()){
        if((newCategory = userCategories.get(title)) == null){

            newCategory = new Category(title, type);
            userCategories.put(title, newCategory);
        }
       }
       else
        newCategory = new Category(title, type);

        return newCategory;
    }

    public boolean addTransaction(int value, String date, String description, String category){

        Category newCategory;
        if((newCategory = userCategories.get(category)) == null){
            return false;
        }
        else {
            Transaction newTransaction = new Transaction(value, date, description);
            newCategory.addTransaction(newTransaction);
            return true;
        }
    }

    public String getUsername(){
        return username;
    }

    public int getTotalSaved(){
        return totalSaved;
    }

    public int getAvailableMonth(){

        return availableMonth;
    }


}
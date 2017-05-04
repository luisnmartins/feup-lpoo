package lpoo.pocketsave.Logic;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    String username;
    int totalSaved;
    int availableMonth;
    HashMap<String, Category> userCategories;

    public User(String username, int totalSaved, HashMap<String, Category> oldCategories){

        this.username = username;
        this.totalSaved = totalSaved;
        this.userCategories = oldCategories;
    }

    public boolean addCategory(String title, Category newCategory){

        for(int i=0; i<userCategories.size(); i++)

             if(userCategories.get(i).equals(newCategory))
                 return false;

        userCategories.put(title, newCategory);
        return true;
    }


}

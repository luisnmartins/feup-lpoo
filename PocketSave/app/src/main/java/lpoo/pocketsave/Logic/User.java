package lpoo.pocketsave.Logic;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {

    private static final String TAG = "User";

    private String email;
    private double totalSaved;
    private String password;
    private String since;
    private long id;


    /**
     * Constructor. Start user maps and variables.
     */
    public User(long id, String email, String password){

        this.id = id;
        this.email = email;
        this.password = password;
        updateTotalSaved();
    };

    private void updateTotalSaved(){
        totalSaved =0;
        Date d = new Date();
        HashMap<String, Double> spents = DataManager.getInstance().getTotalSpentValues("Type", null,since,d.getInitialDate(true, "current"), true);
        if(spents == null)
            totalSaved =0;
        else {
            for (HashMap.Entry<String, Double> it : spents.entrySet()) {
                if(it.getKey().equals("Income"))
                    totalSaved += it.getValue();
                else
                    totalSaved-= it.getValue();
            }
        }

    }

    public void setSince(String data){
        this.since = data;
    }


    public User(String email, String password){

        this.email = email;
        this.id = -1;
        totalSaved = 0;
    }

    public String getEmail(){
        return email;
    }



    public double getTotalSaved(){
        return totalSaved;
    }


    public long getID(){
        return id;
    }

    public void setID(long id){
        this.id = id;
    }

    public String getPassword(){
        return password;
    }


}
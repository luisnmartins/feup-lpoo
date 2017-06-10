package lpoo.pocketsave.Logic;


import android.util.Log;

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
        totalSaved = 0;

    };


    /**
     * Updates total saved value with all avings from the beginning until the end of the last month
     */
    public void updateTotalSaved(){
        totalSaved =0;
        Date d = new Date();
        HashMap<String, Double> spents = DataManager.getInstance().getTotalSpentValues("Type", null,since,d.getInitialDate(false, "last"), true);

        if(spents == null) {

            totalSaved = 0;
        }
        else {
            for (HashMap.Entry<String, Double> it : spents.entrySet()) {
                Log.d(TAG,"ENTROU");
                if(it.getKey().equals("Income"))
                    totalSaved += it.getValue();
                else
                    totalSaved-= it.getValue();
            }
        }

        Log.d(TAG,"TOTAL SAVED " + totalSaved);

    }

    /**
     * Set registration date of the user
     * @param date Registration date
     */
    public void setSince(String date){
        this.since = date;
    }

    /**
     * Get the registration date of the user
     * @return Returns the registration date of the user
     */
    public String getSince(){
        return since;
    }


    /**
     * Gets user's email
     * @return Returns user's email
     */
    public String getEmail(){
        return email;
    }


    /**
     * Update and get user's total saved
     * @return Returns user's total saved
     */
    public double getTotalSaved(){

        updateTotalSaved();
        return totalSaved;
    }

    /**
     * Get user's ID
     * @return Returns user's ID
     */
    public long getID(){
        return id;
    }

    /**
     * Get user's password
     * @return Returns user's password
     */
    public String getPassword(){
        return password;
    }


}
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
        Calendar c = Calendar.getInstance();
        String currentDate = c.get(Calendar.YEAR)+"-"+String.format("%02d", c.get(Calendar.MONTH)+1)+String.format("%02d",c.get(Calendar.DAY_OF_MONTH));
        totalSaved =0;
        HashMap<String, Double> spents = DataManager.getInstance().getTotalSpentValues("Type", null,since, currentDate, true);

        for(HashMap.Entry<String ,Double> it: spents.entrySet()){
            totalSaved +=it.getValue();
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
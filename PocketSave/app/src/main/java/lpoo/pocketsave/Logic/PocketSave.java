package lpoo.pocketsave.Logic;

import android.content.Context;

/**
 * Created by luisnmartins on 03/05/2017.
 */

public class PocketSave {
    DatabaseHelper myDB;
    User currUser;
    static private PocketSave instance = null;

    static public PocketSave getInstance(){
        if(instance == null)
            instance = new PocketSave();
        return instance;

    }

    private PocketSave(){

        //this.myDB = myDB;

    }



}

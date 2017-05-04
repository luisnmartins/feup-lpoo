package lpoo.pocketsave.Logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by luisnmartins on 03/05/2017.
 */

public class PremiumUser extends User {

    double monthlyPayment;
    Date montlyPaymentDate;
    Suggestions suggestion;

    public PremiumUser(String username, int totalSaved, HashMap<String, Category> oldCategories) {
        super(username, totalSaved, oldCategories);
    }
}

package lpoo.pocketsave.Logic;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by luisnmartins on 06/06/2017.
 */

public class Date {

    ArrayList<Integer> daysofmonths = new ArrayList<Integer>();

    public Date(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        daysofmonths.add(31);
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0)
                    daysofmonths.add(29);
                else
                    daysofmonths.add(28);
            } else
                daysofmonths.add(29);
        } else
            daysofmonths.add(28);
        daysofmonths.add(31);
        daysofmonths.add(30);
        daysofmonths.add(31);
        daysofmonths.add(30);
        daysofmonths.add(31);
        daysofmonths.add(31);
        daysofmonths.add(30);
        daysofmonths.add(31);
        daysofmonths.add(30);
        daysofmonths.add(31);
    }

    /**
     * Returns
     * @param index
     * @return
     */
    public int getDaysofMonth(int index){
        return daysofmonths.get(index);
    }


    /**
     * @param firstDay
     * @param month
     * @return
     */
    public String getInitialDate(Boolean firstDay, String month) {
        Calendar c = Calendar.getInstance();
        String date;
        String monthget;
        if (month.equals("currentDate")) {
            date = c.get(Calendar.YEAR) + "-" + String.format("%02d", (c.get(Calendar.MONTH) + 1)) + "-" + String.format("%02d", c.get(Calendar.DAY_OF_MONTH));
            return date;
        }
        if (firstDay) {
            if (month.equals("last")) {
                if ((c.get(Calendar.MONTH)) == 0) {
                    return null;
                }
                date = c.get(Calendar.YEAR) + "-" + String.format("%02d", (c.get(Calendar.MONTH))) + "-0" + 1;
            } else {

                date = c.get(Calendar.YEAR) + "-" + String.format("%02d", (c.get(Calendar.MONTH) + 1)) + "-0" + 1;

            }

        } else {
            if (month.equals("last")) {

                if ((c.get(Calendar.MONTH)) == 0) {
                    return null;
                }
                date = c.get(Calendar.YEAR) + "-" + String.format("%02d", (c.get(Calendar.MONTH))) + "-" + daysofmonths.get(c.get(Calendar.MONTH) - 2);

            } else {
                date = c.get(Calendar.YEAR) + "-" + String.format("%02d", ((c.get(Calendar.MONTH))+1)) + 1 + "-" + daysofmonths.get(c.get(Calendar.MONTH) - 1);
            }
        }
        return date;
    }
}

package lpoo.pocketsave.Logic;

import java.util.ArrayList;
import java.util.Calendar;


public class Date {

    private final ArrayList<Integer> daysofmonths = new ArrayList<>();

    /**
     * Create a date and array of number of days
     */
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
     * Gets the number of days of a specific month
     * @param index Index of month to get days
     * @return Returns the number of days of a specific month
     */
    public int getDaysofMonth(int index){
        return daysofmonths.get(index);
    }


    /**
     * Get a String with the date asked
     * @param firstDay True if is the first day of the month and false if not
     * @param month string to set the month to get or the current Date - "last", "current" and "currentDate"
     * @return Returns the asked date
     */
    public String getInitialDate(Boolean firstDay, String month) {
        Calendar c = Calendar.getInstance();
        String date;
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

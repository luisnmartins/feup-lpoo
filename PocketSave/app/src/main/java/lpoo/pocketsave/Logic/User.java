package lpoo.pocketsave.Logic;


import java.text.DateFormat;

public class User {

    private static final String TAG = "User";

    private String email;
    private double totalSaved;
    private String password;
    /*private int availableMonth;*/
    private long id;
    DateFormat df1;


    /**
     * Constructor. Start user maps and variables.
     */
    public User(long id, String email, String password, double totalSaved){

        this.id = id;
        this.email = email;
        this.password = password;
        this.totalSaved = totalSaved;

        //df1 = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);


    };

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

    /*public void addType(String title, long id){
        types.put(title, id);
    }

    /**
     *
     * @param title
     * @param type
     * @return
     */
    /*public Category addCategory(String title, String type){

        //verify if category doesn't already exist or if type doesn't exist
        if(categories.get(title) != null || types.get(type) == null) {
            Log.d(TAG, "New category was not added\n");
            return null;
        }
        else{

            Category newCategory = new Category(title, types.get(type), this.id);
            categories.put(title, newCategory);
            Log.d(TAG, "New category was added successfully\n");
            return newCategory;
        }
    }



   /* /**
     * Login the user in the database and set his values in user singleton class using setUser function
     * @param email user email
     * @param password user password
     * @return Returns true if the user logged in with success and false if not
     */
   /* public boolean signin(String email, String password){


        Cursor userInfo = DatabaseSingleton.getInstance().getDB().openUser(email, password);
        if(userInfo != null){

            setUser(userInfo);
            return true;
        }
        return false;
    }*/



    /**
     * Set user settings in the user singleton class
     * @param userInfo Cursor containing the user data from database
     */
    /*public void setUser(Cursor userInfo){

        this.username = userInfo.getString(userInfo.getColumnIndex(USER_EMAIL));
        this.totalSaved = userInfo.getDouble(userInfo.getColumnIndex(USER_TOTALSAVED));
        this.id = userInfo.getInt(userInfo.getColumnIndex(USER_ID));
        setTypes();



    }



    public void setTypes(){

        addType("income");
        addType("fixed expense");
        addType("variable expense");

    }

    /**
     *
     * @param title
     * @return
     */
    /*public int getCategoryID(String title){

       returnc.get(title).getID();
    }*/

    /*/**
     * Add a new category to categories hashmap
     * @param title Name of the category
     * @param type Type's name of the category
     * @return Returns the category or null if it was not created
     */
    /*public Category addCategory(String title, String type){

        //get type id
        Integer typeID;
        if((typeID= types.get(type)) == null){
            System.out.println("This type does not exist");
            return null;
        }

        //Verify if this category doesn't already exist, and if not, add it
        if(categories.get(title) == null){

            //create category
            Category newCategory = new Category(title, typeID, this.id);
            categories.put(title, newCategory);
            Log.d("User class: ", "New category added\n");
            return newCategory;
        }
        else
            return null;
    }

    /*public Category getCategory(String title){
        return .get(title);
    }*/

   /* public boolean addType(String title){


        if(types.get(title) == null){

            Log.d("User class: ","New type added\n");
            this.types.put(title, id);
            return true;
        }
        return false;
    }



    /*public boolean addTransaction(int value, String date, String description, String category){

        Category newCategory;
        if((newCategory = userCategories.get(category)) == null){
            System.out.println("Error getting category\n");
            return false;
        }
        else {
            Transaction newTransaction = new Transaction(value, date, description);
            //newCategory.addTransaction(newTransaction);
            return true;
        }
    }*/

    //TODO: transactions
   /* public Map<Category, TreeSet<Transaction>> getAllTransactionsBetween(String dS1, String dS2){


        Map<Category, TreeSet<Transaction>> allTrans = new HashMap<Category, TreeSet<Transaction> >();
        try {

            Date d1 = df1.parse(dS1);
            Date d2 = df1.parse(dS2);
            TreeSet<Transaction> temporary;


            for(HashMap.Entry<String, Category> it: userCategories.entrySet()){

                temporary = it.getValue().getTransactionsBetween(d1, d2);
                allTrans.put(it.getValue(), temporary);
            }
            return allTrans;


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;


    }


    public Map<Category, Double> getCategoriesSpents(String dS1, String dS2){

        Map<Category, Double> totalSpentsCategories = new HashMap<Category, Double> ();
        try {

            Date d1 = df1.parse(dS1);
            Date d2 = df1.parse(dS2);
            double totalSpent;


            for(HashMap.Entry<String, Category> it: userCategories.entrySet()){

                totalSpent = it.getValue().getTransactionsTotalValue(d1, d2);
                totalSpentsCategories.put(it.getValue(), totalSpent);
            }
            return totalSpentsCategories;


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }




    public Transaction addTransaction(double value, String date, String description, boolean done) {

        int transID;
        if((transID = DatabaseSingleton.getInstance().getDB().addTransaction(value, date, description, catID, done)))

            Transaction transaction=null;
        try {
            transaction = new Transaction(value, date, description, this.id, done);
        }catch (NoSuchElementException a){

            if(a.getMessage() == "Transaction")
                return null;
        }
        transactions.add(transaction);
        totalSpent += value;
        return transaction;
    }*/
}
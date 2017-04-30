# LPOO1617_T3G03

Java Project - object oriented programming



## Members Group

    Carlos Miguel da Silva de Freitas - up201504749
    Luís Noites Martins - up201503344
    
<br><br>
## Application Class Diagram

![alt text](https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/PocketSaveUML.png)

### Each Class brief description<br>

  **PocketSave** - main class of the application that verifies sign in/up of an user and creates an instance the DB to be used to add and get user's informations.<br>
  
  **User** - has an instance of financialsettings and statistics of the user. Set account settings ( email, password)<br>
  
  **PremiumUser** - has an instance of suggestions. responsible for verifying monthly payment validation ( date and value).<br>
  
  **DataBase** - add and get all the informations from the DB.<br>
  
  **Statistics** - responsible for checking and getting the values necessary to draw charts of them<br>
  
  **FinancialSettings** -  set user's financial settings (incomes, fixed expenses and montly estimated values)<br>
  
  **Suggestions** - responsible for making calculations to get estimated values of the user expenses so that it can,according to the                     results,give,if needed, a specific "alert" to the user <br>

## GUI DESIGN

![alt text](https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Intro.png)

![alt text](https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Sign%20In.png)

![alt text](https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/SignUp.png)

![alt text](https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Month.png)

![alt text](https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Main.png)

![alt text](https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Transaction.png)

![alt text](https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Category.png)

![alt text](https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Income_FixedExpense.png)

![alt text](https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Overview.png)

![alt text](https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Statistics.png)



## UNIT TESTING

.Verify Sign in
.Add/Edit transactions
.Add/Edit/Remove category
.Add/Edit/Remove User
.Add/Edit/Remove Income/Fixed Expense
.Verify total balance
.Verify category balance
.Verify transactions between dates
.Verify new month
.Verify save receipt image
.Verify set menu category
.Verify suggestions (for specific cases)

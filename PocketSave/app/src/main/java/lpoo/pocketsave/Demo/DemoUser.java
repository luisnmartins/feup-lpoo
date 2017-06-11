package lpoo.pocketsave.Demo;


import lpoo.pocketsave.Logic.*;

public class DemoUser {

    public void addLastMonthTransactions(){
        Transaction income1 = new Transaction(1500, "2017-05-01", 1, false, false);
        income1.setDescription("Income");
        Transaction fixedExpense1 = new Transaction(800, "2017-05-01", 2, false, false);
        fixedExpense1.setDescription("expense");
        Transaction income = new Transaction(1500, "2017-05-01", 1, true, false);
        income.setDescription("Income");
        Transaction fixedExpense = new Transaction(800, "2017-05-01", 2, true, false);
        fixedExpense.setDescription("expense");
        Transaction setEat = new Transaction(90, "2017-05-01", 3, false, false);
        setEat.setDescription("setEat");
        Transaction setTransport = new Transaction(80, "2017-05-01", 4, false, false);
        setTransport.setDescription("setTransport");
        Transaction setHealth = new Transaction(150, "2017-05-01", 5, false, false);
        setHealth.setDescription("setHealth");
        Transaction setClothes = new Transaction(100, "2017-05-01", 6, false, false);
        setClothes.setDescription("setClothes");
        Transaction setJoy = new Transaction(30, "2017-05-01", 7, false, false);
        setJoy.setDescription("setJoy");
        Transaction setFood = new Transaction(250, "2017-05-01", 8, false, false);
        setFood.setDescription("setFood");
        Transaction eat1 = new Transaction(18, "2017-05-01", 3, true, true);
        eat1.setDescription("Restaurante chinês");
        Transaction eat2 = new Transaction(20, "2017-05-08", 3, true, true);
        eat2.setDescription("Marisqueira");
        Transaction eat3 = new Transaction(15, "2017-05-15", 3, true, true);
        eat3.setDescription("Restaurante praia");
        Transaction eat4 = new Transaction(15, "2017-05-22", 3, true, true);
        eat4.setDescription("Restaurante praia");
        Transaction eat5 = new Transaction(13, "2017-05-29", 3, true, true);
        eat5.setDescription("Restaurante chines");
        Transaction t1 = new Transaction(35, "2017-05-01", 4, true, true);
        t1.setDescription("Revisao Carro");
        Transaction t2 = new Transaction(10, "2017-05-07", 4, true, true);
        t2.setDescription("Gasolina");
        Transaction t3 = new Transaction(10, "2017-05-28", 4, true, true);
        t3.setDescription("Gasolina");
        Transaction h1 = new Transaction(15, "2017-05-04", 5, true, true);
        h1.setDescription("Farmacia Alves");
        Transaction h2 = new Transaction(50, "2017-05-12", 5, true, true);
        h2.setDescription("Consulta Dentista");
        Transaction h3 = new Transaction(50, "2017-05-20", 5, true, true);
        h3.setDescription("Consulta Oftalmologista");
        Transaction h4 = new Transaction(100, "2017-05-25", 5, true, true);
        h4.setDescription("Oculista");
        Transaction c1 = new Transaction(20, "2017-05-02", 6, true, true);
        c1.setDescription("Casaco Zara");
        Transaction c2 = new Transaction(15, "2017-05-08", 6, true, true);
        c2.setDescription("Pull and Bear");
        Transaction c3 = new Transaction(12, "2017-05-09", 6, true, true);
        c3.setDescription("Seaside");
        Transaction c4 = new Transaction(15, "2017-05-24", 6, true, true);
        c4.setDescription("Cortefiel");
        Transaction j1 = new Transaction(5, "2017-05-07", 7, true, true);
        j1.setDescription("Cinema");
        Transaction j2 = new Transaction(10, "2017-05-14", 7, true, true);
        j2.setDescription("Bowling");
        Transaction j3 = new Transaction(5, "2017-05-21", 7, true, true);
        j3.setDescription("Cinema");
        Transaction j4 = new Transaction(10, "2017-05-28", 7, true, true);
        j4.setDescription("Discoteca");
        Transaction f1 = new Transaction(12, "2017-05-01", 8, true, true);
        f1.setDescription("Peixaria");
        Transaction f2 = new Transaction(40, "2017-05-07", 8, true, true);
        f2.setDescription("Continente");
        Transaction f3 = new Transaction(25, "2017-05-14", 8, true, true);
        f3.setDescription("Lidl");
        Transaction f4 = new Transaction(30, "2017-05-20", 8, true, true);
        f4.setDescription("Pingo Doce");
        Transaction f5 = new Transaction(25, "2017-05-21", 8, true, true);
        f5.setDescription("Talho");
        Transaction f6 = new Transaction(45, "2017-05-28", 8, true, true);
        f6.setDescription("Pingo Doce");

        DataManager.getInstance().addUpdateTransaction("Add", income1);
        DataManager.getInstance().addUpdateTransaction("Add", fixedExpense1);
        DataManager.getInstance().addUpdateTransaction("Add", income);
        DataManager.getInstance().addUpdateTransaction("Add", fixedExpense);
        DataManager.getInstance().addUpdateTransaction("Add", setEat);
        DataManager.getInstance().addUpdateTransaction("Add", setTransport);
        DataManager.getInstance().addUpdateTransaction("Add", setHealth);
        DataManager.getInstance().addUpdateTransaction("Add", setClothes);
        DataManager.getInstance().addUpdateTransaction("Add", setJoy);
        DataManager.getInstance().addUpdateTransaction("Add", setFood);
        DataManager.getInstance().addUpdateTransaction("Add", eat1);
        DataManager.getInstance().addUpdateTransaction("Add", eat2);
        DataManager.getInstance().addUpdateTransaction("Add", eat3);
        DataManager.getInstance().addUpdateTransaction("Add", eat4);
        DataManager.getInstance().addUpdateTransaction("Add", eat5);
        DataManager.getInstance().addUpdateTransaction("Add", t1);
        DataManager.getInstance().addUpdateTransaction("Add", t2);
        DataManager.getInstance().addUpdateTransaction("Add", t3);
        DataManager.getInstance().addUpdateTransaction("Add", h1);
        DataManager.getInstance().addUpdateTransaction("Add", h2);
        DataManager.getInstance().addUpdateTransaction("Add", h3);
        DataManager.getInstance().addUpdateTransaction("Add", h4);
        DataManager.getInstance().addUpdateTransaction("Add", c1);
        DataManager.getInstance().addUpdateTransaction("Add", c2);
        DataManager.getInstance().addUpdateTransaction("Add", c3);
        DataManager.getInstance().addUpdateTransaction("Add", c4);
        DataManager.getInstance().addUpdateTransaction("Add", j1);
        DataManager.getInstance().addUpdateTransaction("Add", j2);
        DataManager.getInstance().addUpdateTransaction("Add", j3);
        DataManager.getInstance().addUpdateTransaction("Add", j4);
        DataManager.getInstance().addUpdateTransaction("Add", f1);
        DataManager.getInstance().addUpdateTransaction("Add", f2);
        DataManager.getInstance().addUpdateTransaction("Add", f3);
        DataManager.getInstance().addUpdateTransaction("Add", f4);
        DataManager.getInstance().addUpdateTransaction("Add", f5);


    }


    public void addCurrentMonth(){

        Transaction income = new Transaction(1400, "2017-06-01", 1, false, false);
        income.setDescription("Income");
        Transaction fixedExpense = new Transaction(800, "2017-06-01", 2, false, false);
        fixedExpense.setDescription("expense");
        DataManager.getInstance().addUpdateTransaction("Add", income);
        DataManager.getInstance().addUpdateTransaction("Add", fixedExpense);
    }

    public void addDemoUser(){
        addLastMonthTransactions();
        addCurrentMonth();
    }


}

package lpoo.pocketsave.Logic;


import java.util.Calendar;

public class Suggestions {

    //CORRENTE

    //TODO: verificar se uma categoria esta acima (20%) do seu valor previsto comparativamente com o que ja passou do mes corrente
    public void OnlimitCategory(String category){

        Calendar c = Calendar.getInstance();
        String d1 = c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-01";
        String d2 = c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH);
        //DataManager.getInstance().getTransactions(category, d1, d2);
    }



    //TODO: Verificar o metodo de pagamaneto dominante e se for dinheiro verificar seo valor medio das transacoes
        // e de baixa quantia (<20€) - para categorias que estao muito proximo do limite



    //INICIO MES

    //TODO: comparacao do ultimo mes para cada categoria verificando as que ficaram a baixo e as que ficaram a cima do valor previsto

    //TODO: verificar valores medios para os valores previstos e valores efetivos para cada categoria desde o inicio dos registos

    //TODO: sugestao dos valores aproximados para o mes seguinte tendo em conta a variacao em termos de receitas
    // e de despesas fixas e mantendo a mesma percentagem de poupanca do mes passado



}

# LPOO1617_T3G03

Java Project - object oriented programming


## Members Group

    Carlos Miguel da Silva de Freitas - up201504749
    Luís Noites Martins - up201503344
    
<br><br>
## Application Class Diagram

![alt text](https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/PocketSaveUML.png)
<br>

### Setup/Instalação<br>
    
  **Aplicação** - Para executar o apk, basta instalar o ficheiro disponibilizado num dispositivo android - versao minima 4.4 (API 19)<br>
  
  **Projeto** - Para correr o projeto basta abri-lo com o android studio e compilar. Deve existir um dispositivo ligado, ou um dispositvo emulado para que a aplicacao corra.
  
  <br><br>
### Padrões de Desenho

   **Singleton** - Existem duas classes que usam este padrão de desenho: DatabaseHelper e DataManager. A primeira classe gere  toda a parte de criação e acesso a base de dados. Já a classe DataManager gere a comunicação e transferencia de dados entre a base de dados e as classes de design/cálculo.
   
   **Adapter** - Foi necessário o uso de Adapters personalizados de forma a que o RecyclerView, que lista todas as transacoes de uma categoria, possa listar a partir de um HashMap.

<br><br>
### Decisões Relevantes

Foi implementada uma base de dados para guardar toda a informacao utilizada na aplicacao. Esta base de dados divide-se em 5 classes:<br>
   **DatabaseHelper** - que serve para fazer a criação da base de dados.<br>
   **TransactionDB** - responsável pelas funções de CRUD relativas às transações<br>
   **TransactionDB** - responsável pelas funções de CRUD relativas às transações<br>
   **CategoryDB** responsável pelas funções de CRUD relativas às categorias<br>
   **TypeDB** - responsável pelas funções de CRUD relativas aos tipos de Categorias existentes - “Income”, “Variable Expense” e “Fixed Expense” <br>
   **UserDB** -  responsável pelas funções de CRUD relativas ao User.<br>
   
Para converse entre os tipos de dados da base de dados e os utilizados no projeto ( com divisão por classes) foi utilizada a classe DataManager, responsável pela comunicação com as classes da base de dados e parsing da informação obtida.

<br><br>

### Funcionalidades

Para o desenvolvimento da aplicação, achamos pertinente utilizar os seguintes recursos:

 **Mobile** - Para ser possível ao utilizador associar uma imagem da fatura às suas transacções, foi utilizada a câmara do telemóvel.
 
 **Inteligência Artificial** - Para ajudar o utilizador na sua gestão financeira, foram desenvolvidas algumas funções. Estas funçes baseiam-se no histórico existente e, através deste aconselham-no.


<br><br>

### Dificuldades Encontradas

Uma das maiores dificuldades foi a estruturação da parte lógica. De facto a articulação da base de dados com a estrutura de classes orientadoras da aplicação não foi muito fácil dado a necessidade de compactação das funções de escrita e leitura na base de dados. Outro dos problemas prendia-se com o facto de os valores de retorno das funções de get da base de dados não ser compatível com as classes do programa e haver portanto necessidade de fazer parsing destes mesmos resultados. 


<br><br>

### Conclusões

O realização deste projeto contribuiu para o desenvolvimento das nossa capacidades de trabalho em equipa, bem como para a conceção de projetos utilizando Android (linguagem Java) e bases de dados em SQL.


<br><br>

### Distribuição do Trabalho

Luis Martins - 40h (50%)<br>
    - Implementação da base de dados<br>
    - Implementação do DataManager<br>
    - Implementação dos testes unitários<br>
    - Implementação das funções de sugestão<br>

Carlos Freitas -  (50%)<br>
    - Implementacao de toda a parte gráfica<br>
    - Contribuicao na implementação das sugestões<br>
    - Implementa <br>
<br><br>
## GUI DESIGN
-----
***Intro***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Intro.png" width="250"><br><br>
-----
***Sign In***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Sign%20In.png" width="250"><br><br>
-----
***Sign Up***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/SignUp.png" width="250"><br><br>
-----
***Month***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Month.png" width="600"><br><br>
-----
***Main Screen***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Main.png" width="600"><br><br>
-----
***Transaction Screen***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Transaction.png" width="600"><br><br>
-----
***New Category Screen***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Category.png" width="400"><br><br>
-----
***Income/Fixed Expenses***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Income_FixedExpense.png" width="400"><br><br>
-----
***Transactions Overview***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Overview.png" width="250"><br><br>
-----
***Statistics***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources/Mokups/Mokups/Statistics.png" width="600"><br><br>
-----

## USER MANUAL
-----
***login***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/Login.png" width="250">1 - O utilizador devera escrever o email da conta que ja existe,ou pretende criar<br>
2 - O utlizador coloca a palavra passe correspondente ao email colocado ou a password da conta que pretende criar<br>
3 - Verifica se a combinacao email + password corresponde a user existente. Se nao existir da mensagem de erro e pede ao user para voltar a escrever email+password,caso contrario, verifica se a conta a ser aberta ja deu os valores previstos gastos nas categorias no mes atual, se nao , o user e dirigido diretamente para o SetMonth (exemplo imagem SetMonthWithHistory), de outra forma, o user e dirigido para o menu principal<br>
4 - Cria o user email+password caso essa combinacao nao existe,ao criar o user abre os settings no "modo" de sign up ou seja de forma o utilizador adicionar os seus expenses e incomes.
<br><br>


<br><br>
## UNIT TESTING

Para verificar o código da parte lógica foram realizados vários testes verifição as diferentes funções existentes.

Estes testes encontram-se dentro do projeto na pasta PocketSave/app/src/androidTest/java/lpoo/pocketsave/unitTest

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
***Login***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/Login.png" width="250"><img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/SetMonthWithHistory.png" width="250"><br>
1 - O utilizador devera escrever o email da conta que ja existe,ou pretende criar<br>
2 - O utlizador coloca a palavra passe correspondente ao email colocado ou a password da conta que pretende criar<br>
3 - Verifica se a combinacao email + password corresponde a user existente. Se nao existir da mensagem de erro e pede ao user para voltar a escrever email+password,caso contrario, verifica se a conta a ser aberta ja deu os valores previstos gastos nas categorias no mes atual, se nao , o user e dirigido diretamente para o SetMonth (exemplo imagem SetMonthWithHistory), de outra forma, o user e dirigido para o menu principal<br>
4 - Cria o user email+password caso essa combinacao nao existe,ao criar o user abre os settings no "modo" de sign up ou seja de forma o utilizador adicionar os seus expenses e incomes.
<br><br>

-----
***Add Fixed Value***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/AddFixedValue.png" width="250"><img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/SelectMonths.png" width="250"><br>
1 - Zona correspondente onde o utilizador deverá inserir o titulo do valor fixo que quer guardar (campo obrigatorio), por exemplo, ordenado despesa da luz, etc<br>
2 - Valor que ganha ou gasta por cada "iteracao" do valor fixo<br>
3 - Escolha dos meses do ano atual em que esta transacao e definitivamente realizada, o utilizador ao carregar no "Choose Month" e carregado um Multiple Choice Dialog em que o utilizador escolhe os meses(exemplo do Dialog na imagem SelectMonths.png)<br>

-----
 
***Settings***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/Settings_signup.png" width="250"><img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/SettingsVerifyPass.png" width="250"><br>
Exemplo da view dos Settings quando e criado uma conta.<br>

Atenção pontos 1 e 2 nao sao uteis nesta fase pois so interessa adicionar os Incomes e os Fixed Expense para o utilizador poder fazer o set dos gastos previstos para o mes atual, logo nao e possivel alterar as definicoes de conta nesta fase, para isso e necessario o utilizador abrir os settings a partir do menu principal<br>

1 - Ao carregar e aberto uma dialog (como vimos na imagem SettingsVerifyPass) em que e pedido o utilzador para verificar a pass, permitindo a alteracao do campo do email<br>
2 - Mesma logica do ponto 1 so que permite a alteracao da password<br>
3 - Apos o user adicionar Incomes e Expenses , atraves dos pontos 4 e 5 ao clicar no Set First Month e aberto a pagina do Month<br>
	ATENCAO: este botao so se encontra na primeira fez que um user faz login, em qualquer outra circunstancia este botao e substituido por outro que permite guardar as alteracaos feitas a conta (pontos 1 e 2 )<br>
4 e 5 -Ao carregar no mais e aberto para a janela de AddFixedValue correspondente ao tipo escolhido Income/Expenses. O radioGroup Income/Expense permite escolher quais as transacoes a listar no frameLayout que se encontra na parte inferior (como na imagem SettingsWithIncome)	<br>

-----
***Set Month***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/SetMonth.png" width="250"><br>

1 - Zona que podera aparecer o aviso da AI a dizer que o utilizador gastou demasiado na categoria x <br>
		Botao que abre os settings caso o utilizador queira adicionar mais income/expenses nesse mes 
<br>		
2 - Valor total do Income desse mes<br>
3 - Valor total dos Fixed Expenses desse mes<br>
4 - Botao + - permite o utilizador adicionar uma nova categoria na hora<br>
	Choose Category - Abre um dialog com todas as categorias existentes o utilizador escolhe uma categoria insere o valor que espera gastar nessa categoria (na caixa de texto amount), e da set desse valor
<br>
5 - Balanco estimado no final do mes<br>
6 - Guardar os valores do mes atual<br>

-----
***Main Menu***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/MainMenu.png" width="250"><img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/CategoriesOutOfMenu.png" width="250"><br>
Menu Inicial da App<br>

1 - Campo onde aparece sugestoes dadas pela AI, quando possivel.<br>
2 - See Statistics que quando pressionado e aberto a janela Categories Stats que permite ao user ver uma grafico a demonstrar o gasto em cada categoria,etc...<br>
3 - Balanco do utilizador no mes atual<br>
4 - Botao more que abre o dialog com o resto das categorias existentes de forma o utilizador adicionar transacoes a essas categorias (como na Imagem CategoriesOutOfMenu)<br>
5 - Botoes das categorias pertencentes ao menu principal, ao ser clicado o utilizador podera adicionar uma transacao a essa categoria<br>
6 - Permite adicionar uma nova categoria<br>

-----
***SideBar***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/SideDrawer (2).png" width="250"><img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/EditCategoriesDialog.png" width="250"><br>
1 - Valor total que o utilizador salvou (ou nao) desde que comecou a usar a aplicacao<br>
2 - Email do utilizador<br>
3 - Abre a janela Category Stats (mesma reacao que o My Statistics no menu principal)<br>
4 - Abre uma dialog com as categorias criadas pelo utilizador de forma o utilizador poder altera-las <br>
5 - Abre um dialog que permite o utilizador escolher exatamente 5 categorias (das existentes) que ele queira que aparecam diretamente no menu principal<br>
6 - Abre os settings<br>
7 - Da logout da conta aberta<br>
8 - Sai da aplicacao<br>

-----
***Categories Stats***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/CategoryStats.png" width="250"><img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/CategoryStatsEx.png" width="250"><br>


1 - Layout que apresentara o grafico circular<br>
		exemplo do grafico em CategoryStatsExample cada setor do grafico corresponde ao valor total gasto numa categoria entre as datas dadas, o utilizador ao clicar num setor abrira a lista com todas as transacoes correspondentes a respetiva categoria (como em CategoriesOverview)
 <br>
2 - Escolha das datas <br>
3 - Inicializar o grafico com as transacoes entre as datas escolhidas (caso haja alguma);<br>

-----
***Categories Overview***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/CategoriesOverview.png" width="250"><img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/SeeTransaction1.png" width="250"><br>
Lista de transacoes de uma categoria entre duas datas<br><br>

Cada item da lista corresponde a uma transacao<br>

1 - Valor gasto nessa transacao<br>
2 - Categoria correspondente + cor carateristica dessa categoria<br>
3 - Data exata quando foi realizada a transacao<br>
4 - Botao que permite editar a respetiva transacao<br>
	Se o utilizador carregar num item sem ser no botao descrito em 4, o utilizador podera visualizar as carateristicas da transacao
<br>

-----
***Add Transaction***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/AddTransaction.png" width="250"><img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/ZoomPhotoEx.png" width="250"><br>
Exemplo de Adicionar uma transacao na categoria Eat<br><br>

1 - O user insere o valor gasto na transacao<br>
2 - Abre um dialog  (como na imagem DatePickerDialog) deforma o utilizador escolher a data em que a transacao foi realizado<br>
3 - Descricao (opcional) da transacao realizada<br>
4 - Utilizador escolhe se a transacao foi realizada em dinheiro ou com cartao de credito/multibanco<br>
5 - Abre a camara do telemovel de forma a permitir o utilizador adicionar uma imagem da fatura/recibo da transacao<br>
6 - Faz zoom da imagem tirada pelo utilizador no ponto 5<br>

-----
***Add a Category***<br>
<img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/AddNewCategory.png" width="250"><img src="https://github.com/luisnmartins/LPOO1617_T3G03/blob/final-project-delivery/Resources//LPOOManual/ColorPicker.png" width="250"><br>
Janela em que o utilizador adiciona uma nova categoria<br><br>

1 - Titulo da categoria<br>
2 - Cor carateristica da categoria (visivel na demonstracao do grafico circular), escolhe a cor a partir de um colorpicker (demonstradao na imagem ColorPicker)<br>
3* - Valor estimado que o utilizador prentende gastar no resto do mes atual<br>
	Este parametro nao se encontra presente se o utilizador adicionar uma categoria enquanto se encontra a fazer set do mes
<br>
4 - Guarda a nova categoria<br>

-----




	



<br><br>
## UNIT TESTING

Para verificar o código da parte lógica foram realizados vários testes verifição as diferentes funções existentes.

Estes testes encontram-se dentro do projeto na pasta PocketSave/app/src/androidTest/java/lpoo/pocketsave/unitTest

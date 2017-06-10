> SETUP/INSTALLATION PROCEDURE FOR PROJECT AND APP
...

> DESIGN PATTERS
Singleton - Existem duas classes que usam este padrão de desenho: DatabaseHelper e DataManager. A primeira classe gere toda a parte de criação e acesso a base de dados. Já a classe DataManager gere a comunicação e transferencia de dados entre a base de dados e as classes de design/cálculo.

> RELEVANT DESIGN DECISIONS
Foi implementada uma base de dados para guardar toda a informacao utilizada na aplicacao. Esta base de dados divide-se em 5 classes:
	- DatabaseHelper, que serve para fazer a criação da base de dados.
	-TransactionDB, responsável pelas funções de CRUD relativas às transações
	-TransactionDB, responsável pelas funções de CRUD relativas às transações
	-CategoryDB, responsável pelas funções de CRUD relativas às categorias
	-TypeDB, responsável pelas funções de CRUD relativas aos tipos de Categorias existentes - “Income”, “Variable Expense” e “Fixed Expense”
	-UserDB, responsável pelas funções de CRUD relativas ao User
Para converse entre os tipos de dados da base de dados e os utilizados no projeto ( com divisão por classes) foi utilizada a classe DataManager, responsável pela comunicação com as classes da base de dados e parsing da informação obtida.

> MAJOR DIFICULTIES
Uma das maiores dificuldades foi a estruturação da parte lógica visto que foi a primeira vez que usamos uma base de dados. 

> LESSONS LEARNED
...

> OVERALL TIME SPENT DEVELOPING
...

> WORK DISTRIBUTION
Carlos Freitas(50%)
Impelmentacao de toda a parte grafica
Contribuicao na implementacao das sugestoes


Luís Martins(50%)
Implementacao da base de dados
Implementacao do DataManager
Implementacao dos testes unitários
Implementacao das funcoes de sugestao


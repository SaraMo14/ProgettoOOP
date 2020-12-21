# Looking for a job

#### Sara Montese, Riccardo Terrenzi

## Descrizione generale
Il programma consiste nell'implementazione di un servizio di monitoraggio e ricerca di offerte di lavoro. 

In particolare, un servizio che, in base all'api scelta:

- Mostri le offerte di lavoro full-time presenti su Githubs Jobs, tenendo conto delle parole chiave inserite dall'utente.

- Generi statistiche su uno storico di lavori proposti in differenti stati, tenendo conto delleparole chiave inserite dall'utente.

## Funzionamento generale

![Casi d'uso](https://github.com/SaraMo14/ProgettoOOP/blob/main/usecasefinale.png)

Le richieste che vediamo nel diagramma dei casi d'uso possono essere effettuate sfruttando le seguenti rotte:

|Rotte    | Metodo | Descrizione |  
|---------|------------|-------|
|  "/Instructions" | Get | Rotta che restituisce un elenco di istruzioni utili per effettuare richieste |
|  "/Data"      | Get  | Rotta che restituisce tutte le offerte di lavoro presenti su Github Jobs|
|  "/Filter"    | Post | Rotta che restituisce le offerte di lavoro filtrate in base al filtro inserito dall'utente nel body|
|  "/Stats"     | Post | Rotta che restituisce le statistiche per una o tutte le stati, in base alle parole chiave specificate dall'utente nel body|
|  "/Countries" | Get  | Rotta che restituisce l'elenco di stati sulle quali si possono calcolare le statistiche|

Vediamo alcuni esempi di rotte e filtri applicabili:

- "/Filter"

|Esempio filtro| Spiegazione|
|--------------|------------|
|{"description" : "python"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte riguardanti python|
|{"location":"Germany"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro pubblicate in Germania|
|{"title":"Analyst"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro che hanno questo titolo|
|{"company": "Microsoft"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro in questa azienda|
|{"description":"python", "location":"Germany", "company":"Microsoft", "title":"Analyst"}|  Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro pubblicate in Germania, riguardanti python, con titolo "Analyst", nell'azienda Microsoft |
|{"description":"python", "location" : {"$in" : [Germany, Uk, Spain]}}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro pubblicate in Germania, UK o Spagna, che rispettano le parole chiave inserite dall'utente|
|{"description":{"$and": ["python", "c++", "java"]}, "location" : "Germany"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro pubblicate in Germania che rispettino le parole chiave inserite dall'utente|

Qui di seguito un esempio di filtro inserito nel body:

![filterbody](https://github.com/SaraMo14/ProgettoOOP/blob/main/filter_body.jpg)

Qui, invece, parte del risultato ottenuto applicando il filtro mostrato sopra:

![filterreturn](https://github.com/SaraMo14/ProgettoOOP/blob/main/filter_return.jpg)

- "/Stats"

|Esempio filtro| Spiegazione |
|--------------|-------------|
|{"description": ["python"], "weeks":"3"}|Inserendo questo filtro nel body sarà possibile visualizzare le statistiche riguardanti tutte le offerte su python nelle ultime 3 settimane|
|{"location":"Germany", "weeks":"3"}|Inserendo questo filtro nel body sarà possibile visualizzare le statistiche su tutte le offerte di lavoro pubblicate in Germania nelle ultime 3 settimane|
|{"description":["python", "c++", "java"], "location" : "Germany", "weeks":"3"}|Inserendo questo filtro sarà possibile visualizzare le statistiche sulle offerte di lavoro pubblicate in Germania riguardanti python, java e c++ nelle ultime 3 settimane|
|{"description":["python"], "location" : {"$in" : [Germany, Uk, Spain]}}|Inserendo questo filtro sarà possibile visualizzare le statistiche sulle offerte di lavoro pubblicate in Germania, UK e Spagna riguardanti python nelle ultime 3 settimane|

N.B. Per la rotta "/Stats", riguardo il campo description, è importante inserirlo tra parentesi quadre, come vedremo negli esempi seguenti.

Qui di seguito un esempio di filtro inserito nel body:

![filterreturn](https://github.com/SaraMo14/ProgettoOOP/blob/main/stats_return.jpg)

Nel creare i filtri abbiamo utilizzato due tipi di operatori:

|Operatori nei filtri| Spiegazione|
|--------------------|-------------|
|"$in"| Mostra i risultati che rispettano almeno uno dei filtri|
|"$and"| Mostra i risultati che rispettano tutti i filtri immessi|

## Packages e Classi
Vediamo ora come sono sviluppati i vari packages: 

- Jobs application

![application](https://github.com/SaraMo14/ProgettoOOP/blob/main/Jobs.jpeg)

- Controller

![Controller](https://github.com/SaraMo14/ProgettoOOP/blob/main/Controller.jpeg)

- Services

![Services](https://github.com/SaraMo14/ProgettoOOP/blob/main/Services.jpeg)

- Utils

![Utils](https://github.com/SaraMo14/ProgettoOOP/blob/main/Utils.jpeg)

- Model

![Model](https://github.com/SaraMo14/ProgettoOOP/blob/main/Model.jpeg)

- Filters

![Filters](https://github.com/SaraMo14/ProgettoOOP/blob/main/Filters.jpeg)

- Statistics

![Statistics](https://github.com/SaraMo14/ProgettoOOP/blob/main/Statistics.jpeg)

- Exceptions

![Exceptions](https://github.com/SaraMo14/ProgettoOOP/blob/main/Exceptions.jpeg)

## Sequenze
Andiamo a vedere nello specifico i diagrammi delle sequenze, per vedere quali sono le classi coinvolte e il processo che c'è dietro ogni chiamata:

- "/instructions"

![instructions](https://github.com/SaraMo14/ProgettoOOP/blob/main/instructions_sequence.jpeg)

- "/Data"

![allpages](https://github.com/SaraMo14/ProgettoOOP/blob/main/allpages.jpeg)

- "/countries"

![countries](https://github.com/SaraMo14/ProgettoOOP/blob/main/countries.jpeg)

-"/Filter"

![filter](https://github.com/SaraMo14/ProgettoOOP/blob/main/filter.png)

-"/Stats"

![stats](https://github.com/SaraMo14/ProgettoOOP/blob/main/stats.png)

## Suddivisione del lavoro
- Sara Montese 50%
- Riccardo Terrenzi 50%

## Softwares
- Eclipse 
- Maven
- Spring Boot

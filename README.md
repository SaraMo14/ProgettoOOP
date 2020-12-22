# Looking for a job

#### Sara Montese, Riccardo Terrenzi

## Descrizione generale
L'applicazione consiste nell'implementazione di un servizio di monitoraggio e ricerca di offerte di lavoro, sfruttando l'API GitHub Jobs di GitHub.

In particolare, un servizio che:

- Mostri le offerte di lavoro full-time presenti su Githubs Jobs, tenendo conto delle parole chiave inserite dall'utente.

- Generi statistiche su uno storico di lavori proposti in differenti stati, tenendo conto delle parole chiave inserite dall'utente.

## Funzionamento generale

![Casi d'uso](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/usecasefinale.png)

Le richieste che vediamo nel diagramma dei casi d'uso possono essere effettuate sfruttando le seguenti rotte:

|Rotte    | Metodo | Descrizione | Campo |
|---------|------------|-------|---------|
|  "/Instructions" | Get | Rotta che restituisce un elenco di istruzioni utili per effettuare richieste ||
|  "/Data"      | Get  | Rotta che restituisce tutte le offerte di lavoro presenti su Github Jobs||
|  "/Filter"    | Post | Rotta che restituisce le offerte di lavoro filtrate in base alle parole chiave inserite dall'utente nel body|"title", "company", "description", "location"|
|  "/Stats"     | Post | Rotta che restituisce le statistiche per una o tutte le stati, in base alle parole chiave specificate dall'utente nel body|"description", "location", "months"/"weeks"|
|  "/Countries" | Get  | Rotta che restituisce l'elenco di stati sulle quali si possono calcolare le statistiche||

N.B. è obbligatorio specificare un parametro tra "weeks" e "months".

Vediamo alcuni esempi di rotte e filtri applicabili:

- "/Filter"

|Esempio filtro| Spiegazione|
|--------------|------------|
|{"description" : "python"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte riguardanti python|
|{"location":"Germany"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro pubblicate in Germania|
|{"title":"Engineer"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro che hanno la parola "Engineer" nel titolo|
|{"company": "Microsoft"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro proposte da questa azienda|
|{"description":"python", "location":"US", "company":"Atmosphere TV", "title":"Engineer"}|  Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro pubblicate negli Stati Uniti, riguardanti python, con "Engineer" nel titolo, dall'azienda Atmosphere TV |
|{"description":"python", "location" : {"$in" : ["Germany", "Uk", "Spain"]}}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro pubblicate in Germania, UK o Spagna, che rispettano le parole chiave inserite dall'utente|
|{"description":{"$and": ["python", "c++", "java"]}, "location" : "Germany"}| Inserendo questo filtro nel body sarà possibile visualizzare le offerte di lavoro pubblicate in Germania che rispettino ogni parola chiave inserite dall'utente nella description|

Nel creare i filtri abbiamo utilizzato due tipi di operatori:

|Operatori nei filtri| Spiegazione|campo|
|--------------------|-------------|------|
|"$in"| Mostra i risultati che rispettano almeno uno dei filtri|"location"|
|"$and"| Mostra i risultati che rispettano tutti i filtri immessi |"description"|

Qui di seguito un esempio di filtro inserito nel body:

![filterbody](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/stats_body2.jpg)

Qui, invece, parte del risultato ottenuto applicando il filtro mostrato sopra:

![filterreturn](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/filter_return.jpg)

- "/Stats"

|Esempio filtro| Spiegazione |
|--------------|-------------|
|{"description": ["python"], "weeks":"3"}|Inserendo questo filtro nel body sarà possibile visualizzare le statistiche riguardanti tutte le offerte su python nelle ultime 3 settimane|
|{"location":"Germany", "months":"3"}|Inserendo questo filtro nel body sarà possibile visualizzare le statistiche su tutte le offerte di lavoro pubblicate in Germania nelle ultime 3 settimane|
|{"description":["python", "c++", "java"], "location" : "Germany", "weeks":"3"}|Inserendo questo filtro sarà possibile visualizzare le statistiche sulle offerte di lavoro pubblicate in Germania riguardanti python, java e c++ nelle ultime 3 settimane|
|{"description":["python"], "location" : "all", "weeks":"3"}|Inserendo questo filtro sarà possibile visualizzare le statistiche sulle offerte di lavoro pubblicate in tuttli i Paesi riguardanti python nelle ultime 3 settimane|

N.B. E' possibile scegliere tra months e weeks, così da avere statistiche rispettivamente mensili e settimanali.

N.B. Per la rotta "/Stats", riguardo il campo description, è importante inserire le/a parole/a chiave tra parentesi quadre, come vedremo negli esempi seguenti. 
     Inoltre, per specificare la location su cui effettuare le statistiche, è consigliabile visualizzare prima la lista delle possibili Nazioni tra cui scegliere (tramite l' apposita rotta /Countries).

Qui di seguito un esempio di filtro inserito nel body:

![statsbody](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/stats_body1.jpg)

Qui, invece, parte del risultato ottenuto applicando il filtro mostrato sopra:

![statsreturn](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/stats_return1.jpg)


Per testare l'applicazione è necessario include i file .jar (json_simple_1.1.1.jar, gson_2.8.6.jar) alla build path del progetto.
## Packages e Classi
Vediamo ora come sono sviluppati i vari packages: 

- Jobs application

![application](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/Jobs.jpeg)

- Controller

![Controller](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/Controller.jpeg)

- Services

![Services](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/Services.jpeg)

- Utils

![Utils](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/Utils.jpeg)

- Model

![Model](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/Model.jpeg)

- Filters

![Filters](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/Filters.jpeg)

- Statistics

![Statistics](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/Statistics.jpeg)

- Exceptions

![Exceptions](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/Exceptions.jpeg)

## Sequenze
Qui di seguito i diagrammi delle sequenze, che mostrano quali sono le classi coinvolte e il processo che c'è dietro ogni chiamata:

- "/instructions"

![instructions](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/instructions_sequence.jpeg)

- "/Data"

![allpages](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/allpages.jpeg)

- "/countries"

![countries](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/countries.jpeg)

-"/Filter"

![filter](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/filter.png)

-"/Stats"

![stats](https://github.com/SaraMo14/ProgettoOOP/blob/main/images_readme/stats.png)

## Suddivisione del lavoro
- Sara Montese 50%
- Riccardo Terrenzi 50%

## Softwares
- Eclipse 
- Maven
- Spring Boot

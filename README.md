# Looking for a job

#### Sara Montese, Riccardo Terrenzi

## Descrizione generale
Il programma consiste nell'implementazione di un servizio di monitoraggio e ricerca di offerte di lavoro. 

In particolare, un servizio che, in base all'api scelta:

- Mostri le offerte di lavoro full-time presenti su Githubs Jobs, tenendo conto delle parole chiave inserite dall'utente.

- Generi statistiche su uno storico di lavori proposti in differenti stati, tenendo conto delleparole chiave inserite dall'utente.

## Funzionamento generale

![Casi d'uso](https://github.com/SaraMo14/ProgettoOOP/blob/main/UseCaseDiagram.jpeg)

Le richieste che vediamo nel diagramma dei casi d'uso possono essere effettuate sfruttando le seguenti rotte:

|Rotte    | Metodo | Descrizione |  
|---------|------------|-------|
|  "/Instructions" | Get | Rotta che restituisce un elenco di istruzioni utili per effettuare richieste |
|  "/Data"      | Get  | Rotta che restituisce tutte le offerte di lavoro presenti su Github Jobs|
|  "/Data{id}"  | Get  | Rotta che restituisce l'offerte di lavoro con l'id specificato dall'utente come path variable|
|  "/Filter"    | Post | Rotta che restituisce le offerte di lavoro filtrate in base al filtro inserito dall'utente nel body|
|  "/Stats"     | Post | Rotta che restituisce le statistiche per una o tutte le stati, in base alle parole chiave specificate dall'utente nel body|
|  "/Countries" | Get  | Rotta che restituisce l'elenco di stati sulle quali si possono calcolare le statistiche|

Vediamo alcuni esempi di rotte e filtri applicabili:

|Esempio rotta| Spiegazione |
|-------------|-------------|
|"/Data{id}"| Sostituendo "id" con l'id di un'offerta di lavoro verrà mostrata, appunto, quell'offerta|

|Esempio filtro| Spiegazione|
|--------------|------------|
|{"description" : "python"| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte riguardanti python|
|{"location":"Germany"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro pubblicate in Germania|
|{"title":"Analyst"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro che hanno questo titolo|
|{"company": "Microsoft"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro in questa azienda|
|{"description":"python", "location":"Germany", "company":"Microsoft", "title":"Analyst"}|  Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro pubblicate in Germania, riguardanti python, con titolo "Analyst", nell'azienda Microsoft |
|{"description":"python", "location" : {"$in" : [Germany, Uk, Spain]}}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro pubblicate in Germania, UK o Spagna, che rispettano le parole chiave inserite dall'utente|
|{"description":"$and": [{"python", "c++", "java"], "location" : "Germany"}| Inserendo questo filtro nel body sarà possibile visualizzare tutte le offerte di lavoro pubblicate in Germania che rispettino le parole chiave inserite dall'utente|





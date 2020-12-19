# Looking for a job

#### Sara Montese, Riccardo Terrenzi

## Descrizione generale
Il programma consiste nell'implementazione di un servizio di monitoraggio e ricerca di offerte di lavoro. 

In particolare, un servizio che, in base all'api scelta:

- Mostri le offerte di lavoro full-time presenti su Githubs Jobs, tenendo conto delle parole chiave inserite dall'utente.

- Generi statistiche su uno storico di lavori proposti in differenti stati, tenendo conto delleparole chiave inserite dall'utente.

## Funzionamento generale

![usecasediagram](https://web.whatsapp.com/82a24e83-6dae-4707-a811-6edc0218fcea)




|Rotte    | Tipo | Descrizione |  
|---------|------------|-------|
|  "/Data"      | Get  | Rotta che restituisce tutte le offerte di lavoro presenti su Github Jobs|
|  "/Data{id}"  | Get  | Rotta che restituisce l'offerte di lavoro con l'id specificato dall'utente come path variable|
|  "/Filter"    | Post | Rotta che restituisce le offerte di lavoro filtrate in base al filtro inserito dall'utente nel body|
|  "/Stats"     | Post | Rotta che restituisce le statistiche per una o tutte le stati, in base alle parole chiave specificate dall'utente nel body|
|  "/Countries" | Get  | Rotta che restituisce l'elenco di stati sulle quali si possono calcolare le statistiche|

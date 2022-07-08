
# Hibernate - entity manager

Piccola progetto maven per gestire una pagina di ordini. L’applicazione è costruita con un progetto maven di tipo WAR.

All’interno troviamo due servlet, due pagine JSP, una classe per gestire la connessione, una per le operazioni CRUD e i models per il database.

Vorrei concentrarmi su come ottenere un gestore di entità, avviare la transazione e chiuderla.

Le altre cose sono tutte documentate nelle repo [qui](https://github.com/gducsn?tab=repositories).

---

**JPA Entity Manager**

1) Creare un gestore di entità.

L’interfaccia **EntityManagerFactory** presente in java.persistence è usata per ottenere un gestore di entità.

- **Persistence -** La classe Persistence è una classe di avvio usata per ottenere l’interfaccia EntityManagerFactory
- **createEntityManagerFactory() method -** Il ruolo ****di questo metodo è quello di creare e ritornare un gestore di entità per l’unità di persistenza. Al metodo viene passato il nome dell’unità di persistenza all’interno del file persistence.xml.

```java
private static EntityManagerFactory factory;

	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;
	}
```

Dunque, istanziamo l’interfaccia ‘EntityManagerFactory’ la quale incapsula tutto quello che ci serve per ottenere il gestore. All’interno dell’istanza utilizziamo il metodo ‘createEntityManagerFactory’ e gli passiamo il nome dell’unità di lavoro. Questa stringa deve essere la stessa nel file persistence.xml.

2) Ottenere il gestore di entità.

- **EntityManager** - L’interfaccia che ci permettere del gestore di entità.
- **createEntityManager()** - crea un nuovo gestore.

```java
EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
```

Per ottenere un gestore di entità tramite la nostra unità di lavoro utilizziamo l’interfaccia ‘EntityManager’, richiamiamo il metodo dall’altra classe e creiamo il nostro gestore attraverso il metodo ‘createEntityManager();’.

3) Avviamo il nostro gestore di entità.

- **getTransaction() method -** questo metodo ritorna la risorsa dell’oggetto ‘EntityTransaction’.
- **begin() method -** Questo metodo è usato per avviare la transazione.

```java
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
```

Utilizziamo l’interfaccia ‘EntityTransaction’ e metodo ‘getTransaction’ sulla nostra entità di lavoro. 
Una volta ottenuta la risorsa possiamo avviare la nostra transazione.  

4) Persistiamo i dati all’interno del database relazionale. 

- **persist() -** Questo metodo è usato per rendere un’istanza gestita e persistente. L’istanza dell’entità è passata a questo metodo.

```java
entityManager.persist(tipoPanino);
```

5) Chiudere la transazione.

```java
		entityManager.getTransaction().commit();

		entityManager.close();
```

Questa procedura è la base per ottenere un gestore di entità. Con questo gestore possiamo, appunto, gestire le nostre entità e gestire al meglio i dati nel db. 
’entityManager’ ha tanti metodi per le operazioni crud, li troviamo [qui](https://docs.oracle.com/javaee/7/api/javax/persistence/EntityManager.html).

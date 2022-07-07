package utility;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Ingredienti;
import model.TipoPanino;
import model.User;

public class Crud {

	public void insertEntity(String[] checkbox, String radiobox, User userin) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		User user = userin;
		String checkboxdata = "";

		for (String data : checkbox) {
			if (data != "") {
				checkboxdata += data + " ";
			}

		}
		;

		Ingredienti ingredienti = new Ingredienti(checkboxdata, user);
		TipoPanino tipoPanino = new TipoPanino(radiobox, user);

		List<Ingredienti> listaIngredienti = new ArrayList<Ingredienti>();
		List<TipoPanino> listaTipi = new ArrayList<TipoPanino>();

		listaIngredienti.add(ingredienti);
		listaTipi.add(tipoPanino);

		user.setIngredienti(listaIngredienti);
		user.setTipoPanino(listaTipi);
		entityManager.persist(tipoPanino);
		entityManager.persist(ingredienti);

		entityManager.flush();
		entityManager.getTransaction().commit();

		entityManager.close();

	}

	public void insertEntity2() {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		User student = new User("user", "user");
		entityManager.persist(student);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public User retrieveUser(String username, String password) {
		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		TypedQuery<User> query = entityManager
				.createQuery("select u from User u where u.username=:username AND u.password=:password", User.class);

		query.setParameter("username", username);
		query.setParameter("password", password);
		List<User> listaUser = query.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return listaUser != null && !listaUser.isEmpty() ? listaUser.get(0) : null;

	};

	@SuppressWarnings("unchecked")
	public List<Ingredienti> ingredienti(int id) {

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Query q = entityManager.createNativeQuery("SELECT * FROM food.ingredienti where user_id = ?",
				Ingredienti.class);
		q.setParameter(1, id);
		List<Ingredienti> ingredienti = (List<Ingredienti>) q.getResultList();

		entityManager.getTransaction().commit();
		entityManager.close();

		return ingredienti;

	}

}
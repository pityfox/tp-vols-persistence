package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.ClientDao;
import vol.metier.dao.ReservationDao;
import vol.metier.model.Client;
import vol.metier.model.Reservation;

@Transactional
@Repository
public class ClientDaoJpa implements ClientDao {

	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	
	@Autowired
	private ReservationDao reservationDao;


	@Override
	@Transactional(readOnly=true)
	public Client find(Long id) {
		return em.find(Client.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Client> findAll() {
		Query query = em.createQuery("from Client c left outer join fetch c.login");		
		return query.getResultList();
	}

	@Override
	public void create(Client client) {
		em.persist(client);
	}

	//un objet récupéré de la base est déjà managé donc les modif se font automatiquement pas besoin d'update
	//on utilise update pour merger objet
	@Override
	public Client update(Client client) {
		return em.merge(client);
		
	}

	@Override
	public void delete(Client client) {
		client = em.merge(client);
		for(Reservation resa : client.getReservations()){
			reservationDao.delete(resa);
		}
		em.remove(client.getLogin());
		em.remove(client);
		
	}

	@Override
	public void delete(Long id) {
		Client client = find(id);
		for(Reservation resa : client.getReservations()){
			reservationDao.delete(resa);
		}
		em.remove(client.getLogin());
		em.remove(client);
	
	}

}

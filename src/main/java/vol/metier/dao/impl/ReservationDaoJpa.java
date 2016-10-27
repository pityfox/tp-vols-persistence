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
import vol.metier.model.Reservation;

@Transactional
@Repository
public class ReservationDaoJpa implements ReservationDao {

	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;

	@Override
	@Transactional(readOnly=true)
	public Reservation find(Long id) {
		return em.find(Reservation.class, id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Reservation find(int numero) {
		Query query = em.createQuery("from Reservation r where r.numero = :numero");
		query.setParameter("numero", numero);
		List<Reservation> res = query.getResultList();
		return res.size()>0?res.get(0):null ;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Reservation> findAll() {
		Query query = em.createQuery("from Reservation r");		
		return query.getResultList();
	}

	@Override
	public void create(Reservation reservation) {
		em.persist(reservation);
	}

	//un objet récupéré de la base est déjà managé donc les modif se font automatiquement pas besoin d'update
	//on utilise update pour merger objet
	@Override
	public Reservation update(Reservation reservation) {

		return em.merge(reservation);
		
	}

	@Override
	public void delete(Reservation reservation) {
		reservation = em.merge(reservation);	
		reservation.setClient(null);
		reservation.setPassager(null);
		em.remove(reservation);
		
	}

	@Override
	public void delete(Long id) {
		Reservation reservation = find(id);
		reservation.setClient(null);
		reservation.setPassager(null);
		em.remove(reservation);
		
	}

	@Override
	public Long countByVolNumber(String numeroVol) {
		Query query = em.createQuery("select count(r) from Reservation r join r.vol.compagniesAerienneVol cav where cav.numero=:numero");
		query.setParameter("numero", numeroVol);
		return (long) query.getFirstResult();
	}

}

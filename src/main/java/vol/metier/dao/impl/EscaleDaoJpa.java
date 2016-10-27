package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.EscaleDao;
import vol.metier.model.Escale;
import vol.metier.model.EscaleId;

@Transactional
@Repository
public class EscaleDaoJpa implements EscaleDao {

	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;


	@Override
	public Escale find(EscaleId id) {
		return em.find(Escale.class, id);
		
	}

	@Override
	public List<Escale> findAll() {
		Query query = em.createQuery("from Escale e");		
		return query.getResultList();
	}

	@Override
	public void create(Escale escale) {
		escale.setAeroport(em.merge(escale.getAeroport()));
		escale.setVol(em.merge(escale.getVol()));
		em.persist(escale);
	}

	//un objet récupéré de la base est déjà managé donc les modif se font automatiquement pas besoin d'update
	//on utilise update pour merger objet
	@Override
	public Escale update(Escale escale) {
		return em.merge(escale);
		
	}

	@Override
	public void delete(Escale escale) {
		em.remove(em.merge(escale));
		
	}

	@Override
	public void delete(EscaleId id) {
		Escale escale = find(id);
		em.remove(escale);
	}

}

package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.CompagnieAerienneVolDao;
import vol.metier.model.CompagnieAerienneVol;
import vol.metier.model.CompagnieAerienneVolId;

@Transactional
@Repository
public class CompagnieAerienneVolDaoJpa implements CompagnieAerienneVolDao {

	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;


	@Override
	@Transactional(readOnly=true)
	public CompagnieAerienneVol find(CompagnieAerienneVolId id) {
		return em.find(CompagnieAerienneVol.class, id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public CompagnieAerienneVol find(String numero) {
		Query query = em.createQuery("from CompagnieAerienneVol cav where cav.numero = :numero");
		query.setParameter("numero", numero);
		List<CompagnieAerienneVol> ca = query.getResultList();
		return ca.size()>0?ca.get(0):null ;
	}
	

	@Override
	@Transactional(readOnly=true)
	public List<CompagnieAerienneVol> findAll() {
		Query query = em.createQuery("from CompagnieAerienneVol cav");		
		return query.getResultList();
	}

	@Override
	public void create(CompagnieAerienneVol cav) {
		em.persist(cav);
	}

	//un objet récupéré de la base est déjà managé donc les modif se font automatiquement pas besoin d'update
	//on utilise update pour merger objet
	@Override
	public CompagnieAerienneVol update(CompagnieAerienneVol cav) {
		return em.merge(cav);
		
	}

	@Override
	public void delete(CompagnieAerienneVol cav) {
		em.remove(em.merge(cav));
		
	}

	@Override
	public void delete(CompagnieAerienneVolId id) {
		CompagnieAerienneVol cav = find(id);
		em.remove(cav);
		
	}

}

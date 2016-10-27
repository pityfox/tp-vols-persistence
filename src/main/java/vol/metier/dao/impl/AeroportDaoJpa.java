package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.AeroportDao;
import vol.metier.dao.EscaleDao;
import vol.metier.dao.VilleAeroportDao;
import vol.metier.model.Aeroport;
import vol.metier.model.Escale;
import vol.metier.model.VilleAeroport;

@Transactional
@Repository
public class AeroportDaoJpa implements AeroportDao {

	@PersistenceContext //annotation jpa qui injecte automatiquement l'entity manager
	private EntityManager em;
	
	@Autowired
	private VilleAeroportDao villeAeroportDao;
	
	@Autowired
	private EscaleDao escaleDao;


	@Override
	@Transactional(readOnly=true)
	public Aeroport find(Long id) {
		return em.find(Aeroport.class, id);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Aeroport find(String name) {
		Query query = em.createQuery("from Aeroport a where a.nom = :nom");	
		query.setParameter("nom", name);
		List<Aeroport> aeroport = query.getResultList();
		return aeroport.size()>0?aeroport.get(0):null ;
	}

	@Override
	public List<Aeroport> findAll() {
		Query query = em.createQuery("from Aeroport a");		
		return query.getResultList();
	}

	@Override
	public void create(Aeroport aeroport) {
		em.persist(aeroport);
	}

	//un objet récupéré de la base est déjà managé donc les modif se font automatiquement pas besoin d'update
	//on utilise update pour merger objet
	@Override
	public Aeroport update(Aeroport aeroport) {
		return em.merge(aeroport);
		
	}

	@Override
	public void delete(Aeroport aeroport) {
		aeroport = em.merge(aeroport);
		
		for(VilleAeroport villeAeroport : aeroport.getVilleAeroport()){
			villeAeroportDao.delete(villeAeroport);
		}
		for(Escale escale : aeroport.getEscales()){
			escaleDao.delete(escale);
		}
		
		em.remove(aeroport);
		
	}

	@Override
	public void delete(Long id) {
		Aeroport aeroport = find(id);
		for(VilleAeroport villeAeroport : aeroport.getVilleAeroport()){
			villeAeroportDao.delete(villeAeroport);
		}
		for(Escale escale : aeroport.getEscales()){
			escaleDao.delete(escale);
		}
		em.remove(aeroport);
		
	}


}

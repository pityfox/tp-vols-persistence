package vol.metier.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.VilleAeroportDao;
import vol.metier.dao.VilleDao;
import vol.metier.model.Ville;
import vol.metier.model.VilleAeroport;

@Transactional
@Repository
public class VilleDaoJpa implements VilleDao {

	@PersistenceContext // annotation jpa qui injecte automatiquement l'entity
						// manager
	private EntityManager em;

	@Autowired
	private VilleAeroportDao villeAeroportDao;

	@Override
	@Transactional(readOnly=true)
	public Ville find(Long id) {
		return em.find(Ville.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public Ville find(String name) {
		Query query = em.createQuery("from Ville v where v.nom = :nom");
		query.setParameter("nom", name);
		List<Ville> ville = query.getResultList();
		return ville.size() > 0 ? ville.get(0) : null;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Ville> findAll() {
		Query query = em.createQuery("from Ville v");
		return query.getResultList();
	}

	@Override
	public void create(Ville ville) {
		em.persist(ville);

	}

	@Override
	public Ville update(Ville ville) {
		return em.merge(ville);
	}

	@Override
	public void delete(Ville ville) {
		ville = em.merge(ville);
		for (VilleAeroport villeAeroport : ville.getVilleAeroport()) {
			villeAeroportDao.delete(villeAeroport);
		}
		em.remove(ville);

	}

	@Override
	public void delete(Long id) {
		Ville ville = find(id);
		for (VilleAeroport villeAeroport : ville.getVilleAeroport()) {
			villeAeroportDao.delete(villeAeroport);
		}
		em.remove(ville);
	}

}

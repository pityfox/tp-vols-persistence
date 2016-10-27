package tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import vol.metier.dao.AeroportDao;
import vol.metier.dao.ClientDao;
import vol.metier.dao.CompagnieAerienneDao;
import vol.metier.dao.CompagnieAerienneVolDao;
import vol.metier.dao.EscaleDao;
import vol.metier.dao.LoginDao;
import vol.metier.dao.PassagerDao;
import vol.metier.dao.ReservationDao;
import vol.metier.dao.VilleAeroportDao;
import vol.metier.dao.VilleDao;
import vol.metier.dao.VolDao;
import vol.metier.model.Adresse;
import vol.metier.model.Aeroport;
import vol.metier.model.ClientPhysique;
import vol.metier.model.CompagnieAerienne;
import vol.metier.model.CompagnieAerienneVol;
import vol.metier.model.CompagnieAerienneVolId;
import vol.metier.model.Escale;
import vol.metier.model.Login;
import vol.metier.model.Passager;
import vol.metier.model.Reservation;
import vol.metier.model.TitrePhysique;
import vol.metier.model.Ville;
import vol.metier.model.VilleAeroport;
import vol.metier.model.Vol;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@Rollback(false)
public class TestTPVol {

	@Autowired
	private AeroportDao aeroportDao;
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private CompagnieAerienneDao compagnieAerienneDao;
	@Autowired
	private CompagnieAerienneVolDao compagnieAerienneVolDao;
	@Autowired
	private EscaleDao escaleDao;
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private PassagerDao passagerDao;
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private VilleAeroportDao villeAeroportDao;
	@Autowired
	private VilleDao villeDao;
	@Autowired
	private VolDao volDao;

	@Test
	@Transactional
	public void apopulate() {
		Adresse adrClient = new Adresse();
		adrClient.setAdresse("rue de la paix");
		adrClient.setCodePostal("75001");
		adrClient.setPays("France");
		adrClient.setVille("Paris");

		Adresse adrPassager = new Adresse();
		adrPassager.setAdresse("rue des lapins");
		adrPassager.setCodePostal("45000");
		adrPassager.setPays("Orleans");
		adrPassager.setVille("France");

		Passager passager = new Passager();
		passager.setNom("Bunny");
		passager.setPrenom("Bugs");
		passager.setAdresse(adrPassager);

		Ville villeDep = new Ville();
		Ville villeArr = new Ville();
		Ville villeEscale = new Ville();
		villeDep.setNom("Paris");
		villeArr.setNom("Lima");
		villeEscale.setNom("Geneve");

		Aeroport aeroportDep = new Aeroport();
		aeroportDep.setNom("charles de gaulle");

		Aeroport aeroportArr = new Aeroport();
		aeroportArr.setNom("Lima airport");

		Aeroport aeroportEscale = new Aeroport();
		aeroportEscale.setNom("Geneve airport");

		VilleAeroport vaDep = new VilleAeroport();
		VilleAeroport vaArr = new VilleAeroport();
		VilleAeroport vaEsc = new VilleAeroport();
		vaDep.setAeroport(aeroportDep);
		vaDep.setVille(villeDep);
		vaArr.setAeroport(aeroportArr);
		vaArr.setVille(villeArr);
		vaEsc.setAeroport(aeroportEscale);
		vaEsc.setVille(villeEscale);

		Vol vol = new Vol();
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(2014, Calendar.APRIL, 18, 15, 20, 00);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateDepart = cal.getTime();
		Date heureDepart = cal.getTime();
		cal.set(2015, Calendar.MAY, 4, 15, 35, 00);
		cal.set(Calendar.MILLISECOND, 0);
		Date dateArrivee = cal.getTime();
		Date heureArrivee = cal.getTime();

		vol.setDateDepart(dateDepart);
		vol.setDateArrivee(dateArrivee);
		vol.setHeureArrivee(heureArrivee);
		vol.setHeureDepart(heureDepart);
		vol.setDepart(aeroportDep);
		vol.setArrivee(aeroportArr);

		Vol vol2 = new Vol();
		Calendar cal2 = GregorianCalendar.getInstance();
		cal2.set(2014, Calendar.APRIL, 18, 15, 20, 00);
		cal2.set(Calendar.MILLISECOND, 0);
		Date dateDepart2 = cal2.getTime();
		Date heureDepart2 = cal2.getTime();
		cal2.set(2015, Calendar.MAY, 4, 15, 35, 00);
		cal2.set(Calendar.MILLISECOND, 0);
		Date dateArrivee2 = cal2.getTime();
		Date heureArrivee2 = cal2.getTime();

		vol2.setDateDepart(dateDepart2);
		vol2.setDateArrivee(dateArrivee2);
		vol2.setHeureArrivee(heureArrivee2);
		vol2.setHeureDepart(heureDepart2);
		vol2.setDepart(aeroportDep);
		vol2.setArrivee(aeroportArr);
		
		List<Escale> escales = new ArrayList<Escale>();
		Escale escale = new Escale();
		escale.setAeroport(aeroportEscale);
		escale.setVol(vol);
		escales.add(escale);

		CompagnieAerienne ca = new CompagnieAerienne();
		ca.setNom("AirFrance");

		CompagnieAerienneVol cav = new CompagnieAerienneVol();
		CompagnieAerienneVolId cavId = new CompagnieAerienneVolId();
		cavId.setCompagnieAerienne(ca);
		cavId.setVol(vol);
		cav.setId(cavId);
		cav.setNumero("963");

		CompagnieAerienneVol cav2 = new CompagnieAerienneVol();
		CompagnieAerienneVolId cav2Id = new CompagnieAerienneVolId();
		cav2Id.setCompagnieAerienne(ca);
		cav2Id.setVol(vol2);
		cav2.setId(cav2Id);
		cav2.setNumero("967");
		
		ClientPhysique cp = new ClientPhysique();

		Login logClient = new Login();
		logClient.setAdmin(false);
		logClient.setLogin("rd");
		logClient.setMotDePasse("rd");

		cp.setNom("DESTREZ");
		cp.setPrenom("Raphael");
		cp.setTitre(TitrePhysique.MR);
		cp.setEmail("raphael.destrez@sopra.com");
		cp.setNumeroTel(688888888);
		cp.setAdresse(adrClient);
		cp.setLogin(logClient);

		Reservation res = new Reservation();
		res.setNumero(600);
		res.setPassager(passager);
		res.setVol(vol);
		res.setClient(cp);
		
		Reservation res2 = new Reservation();
		res2.setNumero(601);
		res2.setPassager(passager);
		res2.setVol(vol2);
		res2.setClient(cp);

		passagerDao.create(passager);

		villeDao.create(villeDep);
		villeDao.create(villeArr);
		villeDao.create(villeEscale);

		aeroportDao.create(aeroportDep);
		aeroportDao.create(aeroportArr);
		aeroportDao.create(aeroportEscale);

		villeAeroportDao.create(vaDep);
		villeAeroportDao.create(vaArr);
		villeAeroportDao.create(vaEsc);

		volDao.create(vol);
		volDao.create(vol2);
		
		escaleDao.create(escale);

		compagnieAerienneDao.create(ca);

		compagnieAerienneVolDao.create(cav);
		compagnieAerienneVolDao.create(cav2);
		
		loginDao.create(logClient);

		clientDao.create(cp);

		reservationDao.create(res);
		reservationDao.create(res2);
	}
	
	// TESTS JPQL
	@Test
	public void bjpql(){
		long nbResa = reservationDao.countByVolNumber("963");
		System.out.println("Nb resa : " + nbResa);
	}
	
	// TESTS UNITAIRES

//	@Test
//	public void bcheck() {
//		Passager passager = passagerDao.find("Bunny");
//		Ville villeD = villeDao.find("Paris");
//		Ville villeA = villeDao.find("Lima");
//		Ville villeE = villeDao.find("Geneve");
//		Aeroport aeroportD = aeroportDao.find("charles de gaulle");
//		Aeroport aeroportA = aeroportDao.find("Lima airport");
//		Aeroport aeroportE = aeroportDao.find("Geneve airport");
//		CompagnieAerienneVol cav = compagnieAerienneVolDao.find("963");
//		ClientPhysique cp = clientPhysiqueDao.find("DESTREZ");
//		Login login = loginDao.find("rd");
//		Reservation reservation = reservationDao.find(600);
//
//		assertEquals("Bunny", passager.getNom());
//		assertEquals("Paris", villeD.getNom());
//		assertEquals("Lima", villeA.getNom());
//		assertEquals("Geneve", villeE.getNom());
//		assertEquals("charles de gaulle", aeroportD.getNom());
//		assertEquals("Lima airport", aeroportA.getNom());
//		assertEquals("Geneve airport", aeroportE.getNom());
//		assertEquals("963", cav.getNumero());
//		assertEquals("DESTREZ", cp.getNom());
//		assertEquals("rd", login.getLogin());
//		assertEquals(600, reservation.getNumero());
//
//		passager.setNom("Tom");
//		villeD.setNom("Londres");
//		villeA.setNom("Toronto");
//		villeE.setNom("Lisbone");
//		aeroportD.setNom("Stansted");
//		aeroportD.setNom("Toronto Airport");
//		aeroportD.setNom("Lisbonne Airport");
//
//		passagerDao.update(passager);
//		villeDao.update(villeD);
//		villeDao.update(villeA);
//		villeDao.update(villeE);
//
//	}
//
//	@Test
//	public void cupdateCheck() {
//		Passager passager = passagerDao.find("Tom");
//
//		assertEquals("Tom", passager.getNom());
//	}
//
//	@Test
//	public void ddelete() {
//		Passager passager = passagerDao.find("Tom");
//		passagerDao.delete(passager);
//
//	}
//
//	@Test
//	public void edeleteCheck() {
//		Passager passager = passagerDao.find("Tom");
//		assertEquals(null, passager);
//
//	}

}

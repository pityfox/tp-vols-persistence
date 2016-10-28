package vol.metier.dao;


import vol.metier.model.Reservation;

public interface ReservationDao extends Dao<Reservation, Long> {

	Reservation find(Integer numero);
	Long countByVolNumber(String numeroVol);
}

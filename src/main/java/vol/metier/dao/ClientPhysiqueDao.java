package vol.metier.dao;

import vol.metier.model.ClientPhysique;

public interface ClientPhysiqueDao extends Dao<ClientPhysique, Long> {

	ClientPhysique find(String name);
	
}

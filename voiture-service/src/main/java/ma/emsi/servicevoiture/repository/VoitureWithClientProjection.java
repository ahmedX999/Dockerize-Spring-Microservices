package ma.emsi.servicevoiture.repository;

import ma.emsi.servicevoiture.entity.Client;
import ma.emsi.servicevoiture.entity.Voiture;
//import org.springframework.data.rest.core.config.Projection;

//@Projection(name = "withClient", types = { Voiture.class })
public interface VoitureWithClientProjection {
    Long getId();
    String getMarque();
    String getModele();
    String getMatricule();
    Client getClient(); // Include Client entity within Voiture response
}

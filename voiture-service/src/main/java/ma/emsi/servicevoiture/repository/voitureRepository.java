package ma.emsi.servicevoiture.repository;

import ma.emsi.servicevoiture.entity.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RepositoryRestResource(excerptProjection = VoitureWithClientProjection.class)
public interface voitureRepository extends JpaRepository<Voiture, Long> {
    List<Voiture> findByIdClient(Long id);
    //http://localhost:8089/voitures/search/findByClientId?id=2
    // or
    // all cars of client
    //http://localhost:8089/clients/2/voitures


    /* for add new car
    http://localhost:8089/voitures
    method POST Example:
    {
    "client": "http://localhost:8089/clients/2",
    "id": 3,
    "marque": "WS",
    "modele": "WOOO",
    "matricule": "2023"}
     */


}

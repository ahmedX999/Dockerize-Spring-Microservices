package ma.emsi.servicevoiture.service;

import ma.emsi.servicevoiture.entity.CarResponse;
import ma.emsi.servicevoiture.entity.Client;
import ma.emsi.servicevoiture.entity.Voiture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ma.emsi.servicevoiture.repository.voitureRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class VoitureService {

    private final voitureRepository voitureRepository;
    private final RestTemplate restTemplate;
    private final String URL = "http://localhost:8888/SERVICE-CLIENT/api/client"; //gateway

    @Autowired
    public VoitureService(voitureRepository voitureRepository, RestTemplate restTemplate) {
        this.voitureRepository = voitureRepository;
        this.restTemplate = restTemplate;
    }

    public List<CarResponse> findAll() {
        List<Voiture> voitures = voitureRepository.findAll();
        ResponseEntity<Client[]> response = restTemplate.getForEntity(this.URL, Client[].class);
        System.out.println("***********************");
        System.out.println(response.getBody());
        Client[] clients = response.getBody();

        return voitures.stream().map((Voiture v) -> mapToCarResponse(v, clients)).toList();

    }

    private CarResponse mapToCarResponse(Voiture v, Client[] clients) {
        Client foundClient = Arrays.stream(clients)
                .filter(c -> c.getId().equals(v.getIdClient()))
                .findFirst()
                .orElse(null);

        return CarResponse.builder()
                .id(v.getId())
                .marque(v.getMarque())
                .modele(v.getModele())
                .matricule(v.getMatricule())
                .client(foundClient)
                .build();
    }

    public CarResponse findById(Long id) {
        Voiture voiture = voitureRepository.findById(id).orElse(null);
        System.out.println("***********************");
        System.out.println(voiture.getIdClient());
        Client client = restTemplate.getForObject(this.URL + "/" + voiture.getIdClient(), Client.class);
        return CarResponse.builder()
                .id(voiture.getId())
                .marque(voiture.getMarque())
                .modele(voiture.getModele())
                .matricule(voiture.getMatricule())
                .client(client)
                .build();
    }
}

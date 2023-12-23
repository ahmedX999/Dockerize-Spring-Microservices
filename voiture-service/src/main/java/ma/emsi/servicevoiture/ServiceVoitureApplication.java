package ma.emsi.servicevoiture;


import ma.emsi.servicevoiture.entity.Client;
import ma.emsi.servicevoiture.entity.Voiture;
import ma.emsi.servicevoiture.repository.voitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


@EnableFeignClients()
@SpringBootApplication
public class ServiceVoitureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVoitureApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        restTemplate.setRequestFactory(factory);

        return restTemplate;
    }

    @Bean
    CommandLineRunner initializeMysqlDatabase(voitureRepository carRepository) {
        return args -> {
            carRepository.save(new Voiture(Long.parseLong("1"), "Nissan", "Nissan", "Nissan", Long.parseLong("52")));
            carRepository.save(new Voiture(Long.parseLong("2"), "Audi", "Audi", "Audi", Long.parseLong("53")));
        };
    }
   /* @Bean
    CommandLineRunner initialiserBaseH2(voitureRepository voitureRepo, ClientService clientService) {
        return args -> {
            Client c1 = clientService.clientById(Long.parseLong("1"));
            Client c2 = clientService.clientById(Long.parseLong("2"));

            System.out.println("***********************");
            System.out.println(c1);
            System.out.println(c2);
            Voiture v1 = new Voiture(Long.parseLong("1"), "BMW", "X6", "2019", c1);
            voitureRepo.save(v1);
            Voiture v2 = new Voiture(Long.parseLong("2"), "Mercedes", "C200", "2018", c2);
            voitureRepo.save(v2);
            System.out.println("***********************");
            System.out.println(v1);
            System.out.println(v2);
            System.out.println("***********************");

        };
    }

    */
}

/*
@Component
@FeignClient(name = "service-client")
interface ClientService {

    @GetMapping("/clients/{id}")
    public Client clientById(@PathVariable Long id);
}

 */



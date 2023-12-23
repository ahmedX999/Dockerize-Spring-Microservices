package ma.emsi.servicevoiture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voiture {

    @Id
    private Long id;
    private String marque;
    private String modele;
    private String matricule;
    private Long idClient;


}

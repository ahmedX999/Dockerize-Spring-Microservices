package ma.emsi.servicevoiture.controller;

import ma.emsi.servicevoiture.entity.CarResponse;
import ma.emsi.servicevoiture.service.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class VoitureController {

    private VoitureService voitureService;

    @Autowired
    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    @GetMapping
    public List<CarResponse> findAll() {
        return voitureService.findAll();
    }
    @GetMapping("/{id}")
    public CarResponse findById(@PathVariable Long id) throws Exception{
        return voitureService.findById(id);
    }
}

package comdbbyerik.project.controller;

import org.springframework.web.bind.annotation.RestController;

import comdbbyerik.project.service.ServiceProd_item;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import comdbbyerik.project.entity.Prod_item;
import comdbbyerik.project.repository.Prod_itemRepository;

@RestController
public class ControllerProd_item {

    @Autowired
    private ServiceProd_item eService;
    @Autowired
    private Prod_itemRepository eRepository;
    @Value("${app.name}")
    public String appNAme;
    @Value("${app.version}")
    public String appversion;

    @GetMapping("/version")
    public String getVersion() {
        return appNAme + " -" + appversion;
    }

    // Excessive getter finder all
    @GetMapping("/return-total")
    public ResponseEntity<List<Prod_item>> getTotal() {
        return new ResponseEntity<List<Prod_item>>(eService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("/return-unique/{id}")
    public ResponseEntity<?> getUnique(@PathVariable long id) {
        Optional<Prod_item> prod_itemObj = eRepository.findById(id);
        if (prod_itemObj.isPresent()) {
            return new ResponseEntity<Prod_item>(eService.getByid(id), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Objeto de Nº " + id + " não encotrado");

    }

    @PostMapping("/insert-values")
    public ResponseEntity<?> postMethodName(@Valid @RequestBody Prod_item prod_item) {
        try {
            return new ResponseEntity<Prod_item>(eService.saveAllItems(prod_item), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Codigo em duplicidade encontrado Nº " + prod_item.getCode_item());

        }
    }

    @PutMapping("update-value/{id}")
    public ResponseEntity<Prod_item> putMethod(@Valid @PathVariable Long id , @RequestBody Prod_item prod_item) {
        prod_item.setId(id);
        return new ResponseEntity<Prod_item>(eService.saveAllItems(prod_item), HttpStatus.ACCEPTED);
    }
}

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import comdbbyerik.project.entity.Prod_item;
import comdbbyerik.project.repository.Prod_itemCrud;

@RestController
@CrossOrigin(origins = "*")
public class ControllerProd_item {

    @Autowired
    private ServiceProd_item eService;
    @Autowired
    private Prod_itemCrud eRepository;

    @Value("${app.name}")
    public String appNAme;
    @Value("${app.version}")
    public String appversion;

    @GetMapping("/version")
    public String getVersion() {
        return "API name: " + appNAme + "\nAPI Version: " + appversion + "\nDeveloper: Erik Alves VIlar";
    }

    @GetMapping("/return")
    public ResponseEntity<?> MultiRoutes(@RequestParam(required = false) Long id,
            @RequestParam(required = false) Long code,
            @RequestParam(required = false) String type) {
        if (id != null) {
            Optional<Prod_item> prod_itemObj = eRepository.findById(id);
            if (prod_itemObj.isPresent()) {
                return new ResponseEntity<Prod_item>(eService.getByid(id), HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Objeto de Nº " + id + " não encotrado");
        } else if (code != null) {
            return new ResponseEntity<Prod_item>(eService.getByName(code), HttpStatus.OK);
        } else if (type != null) {
            return new ResponseEntity<List<Prod_item>>(eService.getByType(type), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Prod_item>>(eService.getAllItems(), HttpStatus.OK);
        }
    }

    @GetMapping("/return-id/{id}")
    public ResponseEntity<?> getUnique(@PathVariable long id) {

        Optional<Prod_item> prod_itemObj = eRepository.findById(id);
        if (prod_itemObj.isPresent()) {
            return new ResponseEntity<Prod_item>(eService.getByid(id), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Objeto de Nº " + id + " não encotrado");

    }

    @GetMapping("/return-code/{code}")
    public ResponseEntity<Prod_item> getMethodName(@PathVariable Long code) {
        return new ResponseEntity<Prod_item>(eService.getByName(code), HttpStatus.OK);
    }

    @GetMapping("/return/{type}")
    public ResponseEntity<List<Prod_item>> getByType(@PathVariable String type) {
        return new ResponseEntity<List<Prod_item>>(eService.getByType(type), HttpStatus.OK);
    }

    @GetMapping("/return/{exists}")
    public ResponseEntity<List<Prod_item>> getByExists(@PathVariable String exists) {

        return new ResponseEntity<List<Prod_item>>(eService.getByExists(exists), HttpStatus.OK);
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
    public ResponseEntity<Prod_item> putMethod(@Valid @PathVariable Long id, @RequestBody Prod_item prod_item) {
        prod_item.setId(id);
        return new ResponseEntity<Prod_item>(eService.saveAllItems(prod_item), HttpStatus.OK);
    }

    @DeleteMapping("delete-value/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        Optional<Prod_item> prod_itemObj = eRepository.findById(id);
        if (prod_itemObj.isPresent()) {
            Prod_item p = this.eService.deleteItem(id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Objeto de Nº " + id + " não encotrado!");
    }

    @DeleteMapping("delete-code/{code}")
    public ResponseEntity<String> deleteCode(@PathVariable Long code) {
        return new ResponseEntity<String>(eService.deleteByCode(code) + " :LINES AFFECTED\nCODE DELETED: " + code,
                HttpStatus.OK);
    }
}

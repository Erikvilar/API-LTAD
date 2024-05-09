package comdbbyerik.project.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import comdbbyerik.project.entity.Prod_item;
import comdbbyerik.project.repository.Prod_itemCrud;


@Service
public class ServiceProd_item {

    @Autowired
    private Prod_itemCrud eRepo;

    public List<Prod_item> getAllItems() {
        return eRepo.findAll();
    }

    public Prod_item saveAllItems(Prod_item prod_item) {
            return eRepo.save(prod_item);
    }
    public Prod_item  deleteItem(Long id){
        eRepo.deleteById(id);
       
       return null;
       }

    public Prod_item getByid(Long id) {
        return eRepo.findAllById(id);
    }

    public Prod_item getByName(Long code){
        return eRepo.findByCode_item(code);
    }
    public List<Prod_item> getByType(String type){
        return eRepo.findByType_item(type);
    }
    public List<Prod_item> getByExists(Boolean exists){
        return eRepo.findByExists_item(exists);
    }


}

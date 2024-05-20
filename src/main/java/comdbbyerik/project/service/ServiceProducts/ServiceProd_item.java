package comdbbyerik.project.service.ServiceProducts;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import comdbbyerik.project.entity.products.Prod_item;
import comdbbyerik.project.repositories.ProdRepositories.ProdRepostiory;


@Service
public class ServiceProd_item {

    @Autowired
    private ProdRepostiory eRepo;
    
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

    public Prod_item getByCode(Long code){
        return eRepo.findByCode_item(code);
    }
    public List<Prod_item> getByName(String name){
        return eRepo.findByName_item(name);
    }
   
    public List<Prod_item> getByType(String type){
        try{
            return eRepo.findByType_item(type);
        }catch(Exception e){
            throw new InternalError("Data not found");
        }
       
    }
 
    public List<Prod_item> getByExists(String  exists){
        return eRepo.findByExists_item(exists);
    }
    public List<Prod_item> getByCondition(String  condition){
        return eRepo.findByCondition_item(condition);
    }
   
    public Integer deleteByCode(Long code){
        return eRepo.deleteByCode_item(code);
    }


}

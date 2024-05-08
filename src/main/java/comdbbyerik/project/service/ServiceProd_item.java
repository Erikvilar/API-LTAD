package comdbbyerik.project.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import comdbbyerik.project.entity.Prod_item;
import comdbbyerik.project.repository.Prod_itemRepository;

@Service
public class ServiceProd_item {

    @Autowired
    private Prod_itemRepository eRepo;

    public List<Prod_item> getAllItems() {
        return eRepo.findAll();
    }

    public Prod_item saveAllItems(Prod_item prod_item) {
            return eRepo.save(prod_item);
    }

    public Prod_item getByid(Long id) {
        return eRepo.findAllById(id);
    }

}

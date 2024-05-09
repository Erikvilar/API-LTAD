package comdbbyerik.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import comdbbyerik.project.entity.Prod_item;

@Repository
public interface Prod_itemCrud extends CrudRepository<Prod_item, Long> {


    @SuppressWarnings("null")
    List<Prod_item> findAll();

    Prod_item findAllById(Long id);

    @SuppressWarnings({ "unchecked", "null" })
    Prod_item save(Prod_item prod_item);
    
   @SuppressWarnings("null")
    void  deleteById(Long id);

    @Query("FROM Prod_item where code_item  = :code")
    Prod_item findByCode_item(Long code);
    @Query("FROM Prod_item where type_item  = :type")
    List<Prod_item>findByType_item(String type);

}

package comdbbyerik.project.repository;

import java.util.List;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import comdbbyerik.project.entity.Prod_item;

@Repository
public interface Prod_itemRepository extends CrudRepository<Prod_item, Long> {


    List<Prod_item> findAll();

    Prod_item findAllById(Long id);

    @SuppressWarnings("unchecked")
    Prod_item save(Prod_item prod_item);
    
   void  deleteById(Long id);


}

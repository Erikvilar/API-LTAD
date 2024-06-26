package comdbbyerik.project.repositories.ProdRepositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import comdbbyerik.project.entity.products.Prod_item;

@Repository
public interface ProdRepostiory extends CrudRepository<Prod_item, Long> {


    @SuppressWarnings({ "unchecked", "null" })
    Prod_item save(Prod_item prod_item);

    @SuppressWarnings("null")
    List<Prod_item> findAll();

    Prod_item findAllById(Long id);

    @Query("FROM Prod_item where code_item  = :code")
    Prod_item findByCode_item(Long code);

    @Query("FROM Prod_item where type_item  = :type")
    List<Prod_item> findByType_item(String type);

    @Query("FROM Prod_item where condition_item = :condition")
    List<Prod_item> findByCondition_item(String condition);

    @Query("FROM Prod_item where name_item  = :name")
    List<Prod_item> findByName_item(String name);

    @Query("FROM Prod_item where exists_item  = :exists")
    List<Prod_item> findByExists_item(String exists);

    @Transactional
    @Modifying
    @Query("DELETE from Prod_item p where p.code_item  = :code")
    Integer deleteByCode_item(Long code);

    @SuppressWarnings("null")
    void deleteById(Long id);

}

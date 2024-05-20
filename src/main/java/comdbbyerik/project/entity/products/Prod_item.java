package comdbbyerik.project.entity.products;




import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data
@NoArgsConstructor
@Table(name="tb_prod_item")
public class Prod_item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name_item;

    private String image_item;

    private String type_item;
 
    private String condition_item;

    private String description_item;
    //notation for declare to unique value in db
    
    @Column(unique = true)
    private Long code_item;

    private String exists_item;

    // private Double price_item = 0D;

    @CreationTimestamp
    @Column(name="created_item", nullable = false, updatable = false)
    private Date created_item;
    @UpdateTimestamp
    @Column(name="update_item")
    private Date update_item;
   

}


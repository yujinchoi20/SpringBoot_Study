package Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Delivery {
    
    @Id @GeneratedValue
    private Long id;
    
    private Order order;
    private Address address;
    
    @Enumerated
    private DeliveryStatus status;
}

package Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    private Long id;

    private Member member;
    private List<OrderItem> orderItems = new ArrayList<>();
    private Delivery delivery;
    private LocalDateTime orderDate;

    @Enumerated
    private OrderStatus status;
}

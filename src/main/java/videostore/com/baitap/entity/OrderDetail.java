package videostore.com.baitap.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OrderDetails")
@Data
public class OrderDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Đã đổi từ Product sang Video để khớp với CSDL của bạn
    @ManyToOne
    @JoinColumn(name = "VideoId", nullable = false)
    private Video video;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Price")
    private double price;
}
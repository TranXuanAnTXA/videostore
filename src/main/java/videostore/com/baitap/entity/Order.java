package videostore.com.baitap.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Đã sửa thành Username để map đúng với entity User của bạn
    @ManyToOne
    @JoinColumn(name = "Username", nullable = false)
    private User user;

    private Date orderDate;
    private double totalAmount;
    
    @Column(columnDefinition = "NVARCHAR(50)")
    private String status; 
    
    private String paymentMethod; 

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
}
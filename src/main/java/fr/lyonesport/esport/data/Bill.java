package fr.lyonesport.esport.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bill", nullable = false)
    private Long id;

    @Column(name = "bill_date")
    private LocalDate billDate;

    @Column(name = "fee", precision = 15, scale = 2)
    private BigDecimal fee;

    @Column(name = "is_payed", nullable = false)
    private Integer isPayed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_payement", nullable = false)
    private PaymentMode paymentMode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sale_mode", nullable = false)
    private SaleMode saleMode;

    @OneToMany(mappedBy = "bill")
    private List<Order> lineOrders = new ArrayList<>();

}

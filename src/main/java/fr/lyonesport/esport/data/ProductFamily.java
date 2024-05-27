package fr.lyonesport.esport.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_family")
public class ProductFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product_family", nullable = false)
    private Integer id;

    @Column(name = "libelle", length = 200)
    private String libelle;

    @Column(name = "sale_quantity")
    private Integer saleQuantity;

    @Column(name = "sale_amount", precision = 5, scale = 2)
    private BigDecimal saleAmount;

    @Column(name = "online_product_sale", precision = 5, scale = 2)
    private BigDecimal onlineProductSale;

    @Column(name = "direct_product_sale", precision = 5, scale = 2)
    private BigDecimal directProductSale;

}

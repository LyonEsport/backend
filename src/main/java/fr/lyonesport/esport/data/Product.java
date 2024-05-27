package fr.lyonesport.esport.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "purchase_price", precision = 15, scale = 2)
    private Double purchasePrice;

    @Column(name = "sale_price", precision = 15, scale = 2)
    private Double salePrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_product_family", nullable = false)
    private ProductFamily productFamily;

    @ManyToMany
    @JoinTable(name = "product_stock",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_stock"))
    private List<Stock> stocks = new ArrayList<>();

}

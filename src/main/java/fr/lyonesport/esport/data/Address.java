package fr.lyonesport.esport.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "id_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zip_code")
    private String zip_code;

    @Column(name = "city")
    private String city;

    @Column(name = "number")
    private String number;

    @Column(name = "street")
    private String street;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private AddressType type;

    @ManyToOne
    @JoinColumn(name = "id_country", nullable = false)
    private Country country;

}

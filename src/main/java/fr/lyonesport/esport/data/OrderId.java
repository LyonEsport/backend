package fr.lyonesport.esport.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class OrderId implements Serializable {

    @Serial
    private static final long serialVersionUID = 5912138230983007622L;
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    @Column(name = "id_bill", nullable = false)
    private Long idBill;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderId entity = (OrderId) o;
        return Objects.equals(this.idUser, entity.idUser) &&
                Objects.equals(this.idProduct, entity.idProduct) &&
                Objects.equals(this.idBill, entity.idBill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idProduct, idBill);
    }

}
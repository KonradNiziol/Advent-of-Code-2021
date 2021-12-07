package pl.kniziol.hibernate.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime created;
    private BigDecimal total;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Set<OrderRow> orderRows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<OrderRow> getOrderRows() {
        return orderRows;
    }

    public void setOrderRows(Set<OrderRow> orderRows) {
        this.orderRows = orderRows;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", created=" + created + ", total=" + total + '}';
    }
}

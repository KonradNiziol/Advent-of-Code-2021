package pl.kniziol.hibernate.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
    private BigDecimal price;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private ProductType productType;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})// <- bidirectional  /*(fetch = FetchType.EAGER)*/
    /*@JoinColumn(name = "product_id")   ->  one directional relation*/
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_id")}
            )
    // dzieki temu ze uzylismy seta hibernate nie musi usuwac i dodawac calej kolekcji przy jej modyfikacji
    private Set<Attribute> attributes = new HashSet<>();

    public Product() {
    }

    public void addReview(final Review review) {
        this.reviews.add(review);
        review.setProduct(this);
    }

    public void addAttribute(final Attribute attribute) {
        attributes.add(attribute);
        attribute.getProducts().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }


    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\''
                + ", created=" + created + ", updated=" + updated + ", price=" + price + ", productType=" + productType + '}';
    }
}

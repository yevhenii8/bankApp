package org.domahaiev.bankapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.domahaiev.bankapp.model.enums.ProductName;
import org.domahaiev.bankapp.model.enums.ProductStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "product_name", unique = true)
    @Enumerated(EnumType.STRING)
    private ProductName productName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "product_limit")
    private int productLimit;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(interestRate, product.interestRate) == 0 && productLimit == product.productLimit && Objects.equals(id, product.id) && productName == product.productName && productStatus == product.productStatus && Objects.equals(createdAt, product.createdAt) && Objects.equals(updatedAt, product.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, productStatus, interestRate, productLimit, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName=" + productName +
                ", productStatus=" + productStatus +
                ", interestRate=" + interestRate +
                ", productLimit=" + productLimit +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

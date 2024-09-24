package org.domahaiev.bankapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.domahaiev.bankapp.model.enums.AccountCurrency;
import org.domahaiev.bankapp.model.enums.AccountType;
import org.domahaiev.bankapp.model.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status accountStatus;

    @Column(name = "balance")
    private double balance;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private AccountCurrency accountCurrency;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "accounts_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(balance, account.balance) == 0 && Objects.equals(id, account.id) && Objects.equals(username, account.username) && accountType == account.accountType && accountStatus == account.accountStatus && accountCurrency == account.accountCurrency && Objects.equals(createdAt, account.createdAt) && Objects.equals(updatedAt, account.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, accountType, accountStatus, balance, accountCurrency, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountType=" + accountType +
                ", accountStatus=" + accountStatus +
                ", balance=" + balance +
                ", accountCurrency=" + accountCurrency +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

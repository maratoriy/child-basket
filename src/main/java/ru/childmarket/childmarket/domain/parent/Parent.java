package ru.childmarket.childmarket.domain.parent;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ru.childmarket.childmarket.domain.address.Address;
import ru.childmarket.childmarket.domain.child.Child;
import ru.childmarket.childmarket.domain.customer_order.CustomerOrder;
import ru.childmarket.childmarket.domain.review.Review;
import ru.childmarket.childmarket.domain.subscription.Subscription;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parent")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @CreationTimestamp
    @Column(name = "registration_date", nullable = false)
    private Timestamp registrationDate;

    @Column(name = "login_date")
    private Timestamp loginDate;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<Child> children;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Subscription> subscriptions;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Review> reviews;
}
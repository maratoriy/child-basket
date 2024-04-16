package ru.childbasket.domain.child;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.childbasket.domain.parent.Parent;
import ru.childbasket.domain.subscription.Subscription;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "child")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_id")
    private Long childId;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday_date")
    private Timestamp birthdayDate;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date", nullable = false)
    private Timestamp updatedDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "child")
    private List<Subscription> subscriptions;
}
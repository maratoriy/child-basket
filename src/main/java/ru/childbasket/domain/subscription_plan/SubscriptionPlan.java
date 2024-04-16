package ru.childbasket.domain.subscription_plan;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.childbasket.domain.dish_type.DishType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscription_plan")
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long planId;

    @ManyToOne
    @JoinColumn(name = "dish_type_id", nullable = false)
    private DishType dishType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "meal_frequency")
    private Integer mealFrequency;

    @Column(name = "meal_per_order")
    private Integer mealPerOrder;
}
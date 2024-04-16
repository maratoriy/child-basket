package ru.childbasket.domain.dish;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.childbasket.domain.allergen.Allergen;
import ru.childbasket.domain.dish_type.DishType;
import ru.childbasket.domain.ingredient.Ingredient;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private Long dishId;

    @ManyToOne
    @JoinColumn(name = "dish_type_id", nullable = false)
    private DishType dishType;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "proteins")
    private Integer proteins;

    @Column(name = "fats")
    private Integer fats;

    @Column(name = "carbohydrates")
    private Integer carbohydrates;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "dish_ingredients",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "dish_allergens",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id")
    )
    private Set<Allergen> allergens;
}
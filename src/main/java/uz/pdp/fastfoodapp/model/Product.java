package uz.pdp.fastfoodapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.fastfoodapp.model.enums.PriceRating;
import uz.pdp.fastfoodapp.util.annotations.Price;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    @Price
    private Double price;
    @Price
    private Double discount;
    @OneToOne
    private Attachment attachment;
    private PriceRating priceRating;
    @ManyToMany
    private List<Category> category;
    private UUID restaurantId;

}

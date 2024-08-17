package uz.pdp.fastfoodapp.model;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.fastfoodapp.model.enums.PriceRating;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    @OneToMany
    private List<Address> address;

    @OneToMany
    private List<Product> product;

    private Float rating;

    private PriceRating priceRating;

    @ManyToOne
    private Attachment attachment;
}

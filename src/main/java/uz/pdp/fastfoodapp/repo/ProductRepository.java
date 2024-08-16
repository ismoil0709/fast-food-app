package uz.pdp.fastfoodapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.fastfoodapp.model.Category;
import uz.pdp.fastfoodapp.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);

    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.name = ?1")
    List<Product> findAllByCategory(String category);
    List<Product> findAllByName(String name);
}

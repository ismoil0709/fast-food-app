package uz.pdp.fastfoodapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Category;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    default Category getById(UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Category not found by id -> " + id));
    }

    Optional<Category> findByName(String name);
}

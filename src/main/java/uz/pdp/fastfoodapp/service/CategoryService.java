package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.model.Category;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoryService {
    Category save(Category category);
    Category getById(UUID id);
    Category getByName(String name);
    List<Category> getAll();
}

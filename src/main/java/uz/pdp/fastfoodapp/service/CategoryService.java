package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.CategoryCrudDto;
import uz.pdp.fastfoodapp.model.Category;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoryService {
    Category save(CategoryCrudDto crudDto);
    Category getById(UUID id);
    Category getByName(String name);
    List<Category> getAll();
}

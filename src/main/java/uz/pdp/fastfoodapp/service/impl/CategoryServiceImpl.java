package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.exception.InvalidDataException;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Category;
import uz.pdp.fastfoodapp.repo.CategoryRepository;
import uz.pdp.fastfoodapp.service.CategoryService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(UUID id) {
        if (id == null) throw new InvalidDataException("Id");
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category"));
    }

    @Override
    public Category getByName(String name) {
        if (name == null || name.isBlank() || name.isEmpty()) throw new InvalidDataException("Name");
        return categoryRepository.findByName(name).orElseThrow(() -> new NotFoundException("Category"));
    }

    @Override
    public List<Category> getAll() {
        if (categoryRepository.findAll().isEmpty()) throw new NotFoundException("Category");
        return categoryRepository.findAll();
    }
}

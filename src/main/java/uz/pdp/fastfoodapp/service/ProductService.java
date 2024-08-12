package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.response.ProductDto;
import uz.pdp.fastfoodapp.model.Category;
import uz.pdp.fastfoodapp.model.Product;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {
    Product getById(UUID id);
    Product getByName(String name);
    List<Product> getAllProducts();
    List<Product> getByCategory(String category);
}

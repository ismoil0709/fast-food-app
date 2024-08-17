package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.ProductCrudDto;
import uz.pdp.fastfoodapp.model.Product;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {
    Product save(ProductCrudDto crudDto);
    Product getById(UUID id);
    Product getByName(String name);
    List<Product> getAll();
    List<Product> getByCategory(String category);
    List<Product> getPopular();
    Double getPriceAfterDiscount(UUID productId);
}

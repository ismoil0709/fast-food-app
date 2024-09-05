package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.ProductCrudDto;
import uz.pdp.fastfoodapp.dto.response.ProductDto;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductService {
    ProductDto save(ProductCrudDto crudDto);
    ProductDto getById(UUID id);
    ProductDto getByName(String name);
    List<ProductDto> getAll();
    List<ProductDto> getByCategory(String category);
    List<ProductDto> getPopular();
}

package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.exception.InvalidDataException;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Product;
import uz.pdp.fastfoodapp.repo.AttachmentRepo;
import uz.pdp.fastfoodapp.repo.ProductRepository;
import uz.pdp.fastfoodapp.service.ProductService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AttachmentRepo attachmentRepo;

    @Override
    public Product save(Product product) {
        attachmentRepo.save(product.getAttachment());
        return productRepository.save(product);
    }

    @Override
    public Product getById(UUID id) {
        if (id == null) throw new InvalidDataException("id");
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("product"));
    }

    @Override
    public Product getByName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) throw new InvalidDataException("name");
        return productRepository.findByName(name).orElseThrow(() -> new NotFoundException("product"));
    }

    @Override
    public List<Product> getAll() {
        List <Product> products = productRepository.findAll();
        if (products.isEmpty()) throw new NotFoundException("products");
        return products;
    }

    @Override
    public List<Product> getByCategory(String category) {
        List<Product> products = productRepository.findAllByCategory(category);
        if (products.isEmpty()) throw new NotFoundException("products");
        return products;
    }
}

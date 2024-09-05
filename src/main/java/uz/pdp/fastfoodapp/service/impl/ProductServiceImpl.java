package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.ProductCrudDto;
import uz.pdp.fastfoodapp.dto.response.ProductDto;
import uz.pdp.fastfoodapp.exception.InvalidDataException;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Attachment;
import uz.pdp.fastfoodapp.model.Category;
import uz.pdp.fastfoodapp.model.Order;
import uz.pdp.fastfoodapp.model.Product;
import uz.pdp.fastfoodapp.repo.AttachmentRepo;
import uz.pdp.fastfoodapp.repo.CategoryRepository;
import uz.pdp.fastfoodapp.repo.OrderRepository;
import uz.pdp.fastfoodapp.repo.ProductRepository;
import uz.pdp.fastfoodapp.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final AttachmentRepo attachmentRepo;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto save(ProductCrudDto crudDto) {
        Category category = categoryRepository.getById(crudDto.getCategoryId());
        List<Attachment> attachments = attachmentRepo.findAllById(crudDto.getAttachmentIds());
        Product product = new Product(null,
                crudDto.getName(),
                crudDto.getDescription(),
                crudDto.getPrice(),
                crudDto.getDiscount(),
                attachments,
                crudDto.getPriceRating(),
                category,
                crudDto.getRestaurantId());;
        return new ProductDto(productRepository.save(product));
    }

    @Override
    public ProductDto getById(UUID id) {
        return new ProductDto(productRepository.getById(id));
    }

    @Override
    public ProductDto getByName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) throw new InvalidDataException("name");
        return new ProductDto(productRepository.findByName(name).orElseThrow(() -> new NotFoundException("product")));
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) throw new NotFoundException("products");
        return products.stream().map(ProductDto::new).toList();
    }

    @Override
    public List<ProductDto> getByCategory(String category) {
        List<Product> products = productRepository.findAllByCategory(category);
        if (products.isEmpty()) throw new NotFoundException("products");
        return products.stream().map(ProductDto::new).toList();
    }

    @Override
    public List<ProductDto> getPopular() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) throw new NotFoundException("orders");

        Map<Product, Integer> productCountMap = new HashMap<>();

        for (Order order : orders) {
            for (Product product : order.getProducts()) {
                productCountMap.put(product, productCountMap.getOrDefault(product, 0) + 1);
            }
        }

         return productCountMap.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .map(Map.Entry::getKey)
                 .map(ProductDto::new)
                .toList();
    }

}

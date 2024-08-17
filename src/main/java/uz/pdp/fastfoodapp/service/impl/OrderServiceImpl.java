package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.OrderCrudDto;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Order;
import uz.pdp.fastfoodapp.model.Product;
import uz.pdp.fastfoodapp.model.User;
import uz.pdp.fastfoodapp.repo.OrderRepository;
import uz.pdp.fastfoodapp.repo.ProductRepository;
import uz.pdp.fastfoodapp.repo.UserRepository;
import uz.pdp.fastfoodapp.service.OrderService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public Order save(OrderCrudDto order) {
        return orderRepository.save(dtoToEntity(order));
    }

    @Override
    public Order getById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order"));
    }

    @Override
    public List<Order> getByUserId(UUID userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order"));
        orderRepository.deleteById(id);
    }

    private Order dtoToEntity(OrderCrudDto dto) {
        Order order = new Order();

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NotFoundException("User"));
        order.setUser(user);

        List<Product> products = productRepository.findAllById(dto.getProductIds());
        order.setProducts(products);

        Double totalPrice = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        order.setTotalPrice(totalPrice);

        return order;
    }
}

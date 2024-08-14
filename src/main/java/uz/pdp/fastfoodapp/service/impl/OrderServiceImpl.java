package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Order;
import uz.pdp.fastfoodapp.repo.OrderRepository;
import uz.pdp.fastfoodapp.service.OrderService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
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
}

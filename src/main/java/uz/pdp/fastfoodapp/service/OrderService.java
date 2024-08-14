package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.model.Order;

import java.util.List;
import java.util.UUID;

@Service
public interface OrderService {
    Order save(Order order);
    Order getById(UUID id);
    List<Order> getByUserId(UUID userId);
    List<Order> getAll();
    void deleteById(UUID id);
}

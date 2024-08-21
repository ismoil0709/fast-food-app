package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.RestaurantCrudDto;
import uz.pdp.fastfoodapp.exception.InvalidDataException;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Address;
import uz.pdp.fastfoodapp.model.Attachment;
import uz.pdp.fastfoodapp.model.Product;
import uz.pdp.fastfoodapp.model.Restaurant;
import uz.pdp.fastfoodapp.repo.AddressRepository;
import uz.pdp.fastfoodapp.repo.AttachmentRepo;
import uz.pdp.fastfoodapp.repo.ProductRepository;
import uz.pdp.fastfoodapp.repo.RestaurantRepository;
import uz.pdp.fastfoodapp.service.RestaurantService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final AttachmentRepo attachmentRepo;
    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;

    @Override
    public Restaurant save(RestaurantCrudDto crudDto) {
        List<Attachment> attachments = attachmentRepo.findAllById(crudDto.getAttachmentIds());
        List<Product> products = productRepository.findAllById(crudDto.getProductIds());
        List<Address> addresses = addressRepository.findAllById(crudDto.getAddressIds());

        Restaurant restaurant = new Restaurant(
                null,
                crudDto.getName(),
                addresses,
                products,
                crudDto.getRating(),
                crudDto.getPriceRating(),
                attachments,
                crudDto.getDiscount());

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getById(UUID id) {
        if (id == null) throw new InvalidDataException("ID");
        return restaurantRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Restaurant"));
    }

    @Override
    public List<Restaurant> getAll() {
        if (restaurantRepository.findAll().isEmpty()) throw new NotFoundException("Restaurants");
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getByName(String name) {
        if (name.isEmpty() || name.isBlank()) throw new InvalidDataException("Name");
        return restaurantRepository.findByName(name).orElseThrow(() -> new NotFoundException("Restaurant"));
    }

    @Override
    public void setDiscount(RestaurantCrudDto restaurant, double discount) {
        List<UUID> productIds = restaurant.getProductIds();
        for (UUID productId : productIds) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new NotFoundException("Product"));
            product.setDiscount(discount);
        }
    }
}

package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.model.Address;
import uz.pdp.fastfoodapp.model.Restaurant;
import uz.pdp.fastfoodapp.model.User;

import java.util.List;

@Service
public interface AddressService {
    Address save(Address address);
    Double calculateDistance(Address address1, Address address2);
    Restaurant nearestBranch(User user, List<Restaurant> restaurants);

}
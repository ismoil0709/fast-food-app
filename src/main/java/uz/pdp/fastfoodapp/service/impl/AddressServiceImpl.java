package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.exception.InvalidDataException;
import uz.pdp.fastfoodapp.model.Address;
import uz.pdp.fastfoodapp.model.Restaurant;
import uz.pdp.fastfoodapp.model.User;
import uz.pdp.fastfoodapp.repo.AddressRepository;
import uz.pdp.fastfoodapp.repo.RestaurantRepository;
import uz.pdp.fastfoodapp.service.AddressService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final RestaurantRepository restaurantRepository;


    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Double calculateDistance(Address restaurantAddress, Address userAddress) {
        double dLat = Math.toRadians(restaurantAddress.getLatitude() - userAddress.getLatitude());
        double dLon = Math.toRadians(restaurantAddress.getLongitude() - userAddress.getLongitude());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(userAddress.getLatitude())) * Math.cos(Math.toRadians(restaurantAddress.getLatitude())) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        //6371 means radius of earth
        return 6371 * c;
    }

    @Override
    public Restaurant nearestBranch(User user, List<Restaurant> restaurants) {
        Restaurant nearestRestaurant = null;
        double minDistance = Double.MAX_VALUE;

        for (Restaurant restaurant : restaurants) {
            for (Address address : restaurant.getAddress()) {
                double distance = calculateDistance(user.getAddress(), address);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestRestaurant = restaurant;
                }
            }
        }
        return nearestRestaurant;
    }
}

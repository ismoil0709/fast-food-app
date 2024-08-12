package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.exception.NotFoundException;
import uz.pdp.fastfoodapp.model.Address;
import uz.pdp.fastfoodapp.model.Restaurant;
import uz.pdp.fastfoodapp.model.User;
import uz.pdp.fastfoodapp.repo.AddressRepository;
import uz.pdp.fastfoodapp.repo.RestaurantRepository;
import uz.pdp.fastfoodapp.repo.UserRepository;
import uz.pdp.fastfoodapp.service.AddressService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;


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
    public List<Restaurant> nearestBranch(UUID userId) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User"));
        List<Restaurant> nearestRestaurants = new ArrayList<>();
        double minDistance = Double.MAX_VALUE;

        for (Restaurant restaurant : restaurants) {
            for (Address address : restaurant.getAddress()) {
                double distance = calculateDistance(user.getAddress(), address);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestRestaurants.add(restaurant);
                }
            }
        }
        return nearestRestaurants;
    }

    @Override
    public Address findByUserId(UUID id) {
        return addressRepository.findByUserId(id).orElseThrow(() -> new NotFoundException("Address"));
    }

    @Override
    public Address findByBranchName(String branchName) {
        return addressRepository.findByBranch(branchName).orElseThrow(() -> new NotFoundException("Address"));
    }
}

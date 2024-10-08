package uz.pdp.fastfoodapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.AddressCrudDto;
import uz.pdp.fastfoodapp.dto.request.CalculateDistanceDto;
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
    public Address save(AddressCrudDto address) {
        return addressRepository.save(Address.builder()
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .branch(address.getBranch())
                .userId(address.getUserId())
                .build());
    }

    @Override
    public Double calculateDistance(CalculateDistanceDto calculateDistanceDto) {
        double dLat = Math.toRadians(calculateDistanceDto.getRestaurantAddress().getLatitude() - calculateDistanceDto.getUserAddress().getLatitude());
        double dLon = Math.toRadians(calculateDistanceDto.getRestaurantAddress().getLongitude() - calculateDistanceDto.getUserAddress().getLongitude());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(calculateDistanceDto.getUserAddress().getLatitude()))
                        * Math.cos(Math.toRadians(calculateDistanceDto.getRestaurantAddress().getLatitude())) *
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
                double distance = calculateDistance(new CalculateDistanceDto(user.getAddress(), address));
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

    @Override
    public List<Address> findAll() {
        if (addressRepository.findAll().isEmpty()) {
            throw new NotFoundException("Address");
        }
        return addressRepository.findAll();
    }
}

package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.model.Address;
import uz.pdp.fastfoodapp.model.Restaurant;

import java.util.List;
import java.util.UUID;

@Service
public interface AddressService {
    Address save(Address address);
    Double calculateDistance(Address address1, Address address2);
    List<Restaurant> nearestBranch(UUID userId);
    Address findByUserId(UUID id);
    Address findByBranchName(String branchName);
}
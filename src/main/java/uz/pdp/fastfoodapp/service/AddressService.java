package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.AddressCrudDto;
import uz.pdp.fastfoodapp.dto.request.CalculateDistanceDto;
import uz.pdp.fastfoodapp.model.Address;
import uz.pdp.fastfoodapp.model.Restaurant;

import java.util.List;
import java.util.UUID;

@Service
public interface AddressService {
    Address save(AddressCrudDto address);
    Double calculateDistance(CalculateDistanceDto calculateDistanceDto);
    List<Restaurant> nearestBranch(UUID userId);
    Address findByUserId(UUID id);
    Address findByBranchName(String branchName);
    List<Address> findAll();
}
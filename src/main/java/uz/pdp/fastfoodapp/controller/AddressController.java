package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.fastfoodapp.model.Address;
import uz.pdp.fastfoodapp.service.AddressService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.save(address));
    }
    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(addressService.findByUserId(userId));
    }
    @GetMapping("/get/branch/{name}")
    public ResponseEntity<?> getByBranch(@PathVariable String name) {
        return ResponseEntity.ok(addressService.findByBranchName(name));
    }
    @GetMapping("/nearest/{userId}")
    public ResponseEntity<?> getNearest(@PathVariable UUID userId) {
        return ResponseEntity.ok(addressService.nearestBranch(userId));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(addressService.findAll());
    }
}

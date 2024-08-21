package uz.pdp.fastfoodapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.fastfoodapp.dto.response.SuccessResponse;
import uz.pdp.fastfoodapp.model.DailyDiscount;
import uz.pdp.fastfoodapp.service.DailyDiscountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/daily-discount")
public class DailyDiscountController {
    private final DailyDiscountService dailyDiscountService;
    @PostMapping("/add")
    ResponseEntity<?> updateDailyDiscount(@RequestBody List<DailyDiscount> dailyDiscount) {
        dailyDiscountService.update(dailyDiscount);
        return ResponseEntity.ok(new SuccessResponse("Daily Discount Added"));
    }
}

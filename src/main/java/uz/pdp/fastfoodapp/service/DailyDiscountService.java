package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.model.DailyDiscount;

import java.util.List;

@Service
public interface DailyDiscountService {
    void update(List<DailyDiscount> dailyDiscounts);
}

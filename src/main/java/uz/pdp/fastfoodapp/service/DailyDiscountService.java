package uz.pdp.fastfoodapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.fastfoodapp.dto.request.DailyDiscountDto;

import java.util.List;

@Service
public interface DailyDiscountService {
    void update(List<DailyDiscountDto> dailyDiscounts);
}

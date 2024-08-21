package uz.pdp.fastfoodapp.repo;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.fastfoodapp.model.DailyDiscount;

import java.util.List;
import java.util.UUID;

@Repository
public interface DailyDiscountRepository extends JpaRepository<DailyDiscount, UUID> {
    @Override
    @Cacheable(value = "dailyDiscountEntity")
    List<DailyDiscount> findAll();

    @Override
    @CacheEvict(value = "dailyDiscountEntity",allEntries = true)
    DailyDiscount save(DailyDiscount dailyDiscount);

    @Override
    @CacheEvict(value = "dailyDiscountEntity",allEntries = true)
    void delete(DailyDiscount dailyDiscount);
}

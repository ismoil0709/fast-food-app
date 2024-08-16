package uz.pdp.fastfoodapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.fastfoodapp.model.User;
import uz.pdp.fastfoodapp.model.UserFavourite;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserFavouriteRepository extends JpaRepository<UserFavourite, UUID> {
    Optional<UserFavourite> findByUser(User user);
}
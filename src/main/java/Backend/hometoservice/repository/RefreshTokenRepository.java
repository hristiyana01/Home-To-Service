package Backend.hometoservice.repository;

import Backend.hometoservice.model.RefreshToken;
import Backend.hometoservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    void deleteByUser(User user);
}
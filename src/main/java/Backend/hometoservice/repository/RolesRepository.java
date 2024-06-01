package Backend.hometoservice.repository;

import Backend.hometoservice.authorization.dto.ERole;
import Backend.hometoservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}

package Backend.hometoservice.service.implementation;

import Backend.hometoservice.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.Optional;

public class AuditorAwareImplementation implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof User) {
                Integer userId = ((User) principal).getId();
                return Optional.of(userId);
            }
        }

        return Optional.empty();
    }
}

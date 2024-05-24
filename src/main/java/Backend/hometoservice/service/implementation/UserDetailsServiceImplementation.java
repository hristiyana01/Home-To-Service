//package Backend.hometoservice.service.implementation;
//
////import Backend.hometoservice.authorization.UserDetailsImpl;
//import Backend.hometoservice.model.User;
//import Backend.hometoservice.repository.UserRepository;
//import Backend.hometoservice.service.UserDetailsService;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImplementation implements UserDetailsService {
//    @Autowired
//    UserRepository userRepository;
//
////    @Override
////    @Transactional
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        User user = userRepository.findByUsername(username)
////                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
////
////      //  return UserDetailsImpl.build(user);
////    }
//}
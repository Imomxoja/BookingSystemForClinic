package uz.pdp.doctor.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import uz.pdp.doctor.domain.exceptionHandle.exceptions.UserEmailNotFoundException;
import uz.pdp.doctor.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserEmailNotFoundException {
        System.out.println(email);
        return userRepository.findUserByEmail(email).orElseThrow(() ->
                new UserEmailNotFoundException("User not found with this email " + email));
    }
}

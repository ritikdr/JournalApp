package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;

public class UserDetailsServiceImpTest {

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Mock
    private UserRepository userRepository;

    void loadUserByUsernameTest() {
        when(userRepository.findByUserName(ArgumentMatcher.anyString())).thenReturn(User.builder().userName("ram").password("ram123").build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");
    }
}

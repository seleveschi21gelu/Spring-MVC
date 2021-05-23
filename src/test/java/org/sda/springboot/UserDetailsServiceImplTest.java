package org.sda.springboot;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sda.springboot.entities.UserEntity;
import org.sda.springboot.repositories.UserRepository;
import org.sda.springboot.service.UserDetailsServiceImpl;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserDetailsServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Test
    public void loadUserByUsernameShouldReturnUserDetails() {
        // given
        when(userRepository.findByUsername("oli"))
                .thenReturn(new UserEntity(3, "Papi", "pass"));
//
        // when
        UserDetails userDetails = userDetailsService.loadUserByUsername("oli");
        // then
        Assertions.assertEquals("oli",userDetails.getUsername());

    }

}

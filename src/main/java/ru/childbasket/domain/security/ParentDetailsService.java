package ru.childbasket.domain.security;

import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.childbasket.domain.parent.Parent;
import ru.childbasket.domain.parent.ParentRepository;

@Service
@RequiredArgsConstructor
public class ParentDetailsService implements UserDetailsService {
    private final ParentRepository parentRepository;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Parent parent = parentRepository.findByPhoneNumber(username)
                .orElseThrow(NoSuchElementException::new);

        return ParentDetails.build(parent);
    }
}

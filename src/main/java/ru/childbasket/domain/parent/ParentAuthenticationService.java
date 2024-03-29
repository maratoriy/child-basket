package ru.childbasket.domain.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.childbasket.domain.parent.exceptions.ParentAlreadyExistsException;
import ru.childbasket.domain.parent.exceptions.ParentNotExistsException;
import ru.childbasket.domain.security.ParentDetails;
import ru.childbasket.domain.parent.dto.ParentLoginDto;
import ru.childbasket.domain.parent.dto.ParentRegisterDto;
import ru.childbasket.domain.utils.JwtUtils;

@Service
@RequiredArgsConstructor
public class ParentAuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final ParentRepository parentRepository;
    private final ParentMapper parentMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerParent(ParentRegisterDto registerDto) {
        final String phoneNumber = registerDto.getPhoneNumber();
        if (parentRepository.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new ParentAlreadyExistsException(phoneNumber);
        }
        final Parent parent = parentMapper.map(registerDto);
        parent.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        parentRepository.save(parent);
    }

    @Transactional
    public ResponseCookie loginParent(ParentLoginDto loginDto) {
        final String phoneNumber = loginDto.getPhoneNumber();
        if (parentRepository.findByPhoneNumber(phoneNumber).isEmpty()) {
            throw new ParentNotExistsException(phoneNumber);
        }
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        phoneNumber,
                        loginDto.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        ParentDetails userDetails = (ParentDetails) authentication.getPrincipal();

        return jwtUtils.generateJwtCookie(userDetails);
    }

    public ResponseCookie logoutUser() {
        return jwtUtils.getCleanJwtCookie();
    }
}

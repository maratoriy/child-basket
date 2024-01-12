package ru.childmarket.childmarket.domain.address;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.childmarket.childmarket.domain.address.dto.AddressCreateDto;
import ru.childmarket.childmarket.domain.address.dto.AddressResponseDto;

@RestController
@RequestMapping(value = {"/api/parent/addresses"},
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressResponseDto>> getAddresses(@AuthenticationPrincipal UserDetails userDetails) {
        final List<AddressResponseDto> addressResponseDtos = addressService.getAddresses(userDetails.getUsername());

        return ResponseEntity.ok().body(addressResponseDtos);
    }

    @GetMapping("/add")
    public ResponseEntity<List<AddressResponseDto>> addAddress(@AuthenticationPrincipal UserDetails userDetails,
                                                               @RequestBody AddressCreateDto addressCreateDto) {
        addressService.addAddress(userDetails.getUsername(), addressCreateDto);

        return ResponseEntity.ok().build();
    }
}

package ru.childbasket.domain.address;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.childbasket.domain.address.dto.AddressCreateDto;
import ru.childbasket.domain.address.dto.AddressResponseDto;
import ru.childbasket.domain.parent.Parent;
import ru.childbasket.domain.parent.ParentRepository;
import ru.childbasket.domain.parent.exceptions.ParentNotExistsException;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final ParentRepository parentRepository;
    private final AddressMapper addressMapper;

    @Transactional
    public List<AddressResponseDto> getAddresses(String phoneNumber) {
        final Parent parent = parentRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ParentNotExistsException(phoneNumber)
        );

        return parent.getAddresses().stream().map(addressMapper::map).collect(Collectors.toList());
    }

    @Transactional
    public void addAddress(String phoneNumber, AddressCreateDto addressCreateDto) {
        final Parent parent = parentRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ParentNotExistsException(phoneNumber)
        );

        final Address address = addressMapper.map(addressCreateDto);
        address.setParent(parent);
        parent.getAddresses().add(address);

        parentRepository.save(parent);
    }
}

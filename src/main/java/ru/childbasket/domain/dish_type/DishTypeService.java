package ru.childbasket.domain.dish_type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.childbasket.domain.dish_type.dto.DishTypeResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishTypeService {
    private final DishTypeRepository dishTypeRepository;
    private final DishTypeMapper dishTypeMapper;

    public List<DishTypeResponseDto> getDishTypes() {
        return dishTypeRepository.findAll().stream()
                .map(dishTypeMapper::map)
                .collect(Collectors.toList());
    }
}

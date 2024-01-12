package ru.childmarket.childmarket.domain.dish_type;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.childmarket.childmarket.domain.dish.DishRepository;
import ru.childmarket.childmarket.domain.dish_type.dto.DishTypeResponseDto;

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

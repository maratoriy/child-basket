package ru.childbasket.domain.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.childbasket.domain.dish.dto.DishResponseDto;
import ru.childbasket.domain.dish_type.DishTypeRepository;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final DishTypeRepository dishTypeRepository;
    private final DishFullMapper dishFullMapper;

    @Transactional
    public List<DishResponseDto> getDishes() {
        return dishRepository.findAll().stream()
                .map(dishFullMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<DishResponseDto> getDishesByDishesType(Long dishTypeId) {
        try {
            return dishTypeRepository.findById(dishTypeId).orElseThrow()
                    .getDishes().stream()
                    .map(dishFullMapper::map)
                    .collect(Collectors.toList());
        } catch (NoSuchElementException e) {
            return new ArrayList<>();
        }
    }
}

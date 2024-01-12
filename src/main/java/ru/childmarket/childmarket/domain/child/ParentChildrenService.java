package ru.childmarket.childmarket.domain.child;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.childmarket.childmarket.domain.child.Child;
import ru.childmarket.childmarket.domain.child.ChildMapper;
import ru.childmarket.childmarket.domain.child.dto.ChildCreateDto;
import ru.childmarket.childmarket.domain.child.dto.ChildResponseDto;
import ru.childmarket.childmarket.domain.parent.Parent;
import ru.childmarket.childmarket.domain.parent.ParentRepository;
import ru.childmarket.childmarket.domain.parent.exceptions.ParentNotExistsException;

@Service
@RequiredArgsConstructor
public class ParentChildrenService {
    private final ParentRepository parentRepository;
    private final ChildRepository childRepository;
    private final ChildMapper childMapper;

    @Transactional
    public List<ChildResponseDto> getChildren(String phoneNumber) {
        return parentRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ParentNotExistsException(phoneNumber))
                .getChildren()
                .stream().map(childMapper::map).collect(Collectors.toList());
    }

    @Transactional
    public void addChild(String phoneNumber, ChildCreateDto childCreateDto) {
        final Parent parent = parentRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ParentNotExistsException(phoneNumber));
        final Child child = childMapper.map(childCreateDto);
        child.setParent(parent);
        parent.getChildren().add(child);
        parentRepository.save(parent);
    }
}

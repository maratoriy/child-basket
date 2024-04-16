package ru.childbasket.domain.child;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.childbasket.domain.child.dto.ChildCreateDto;
import ru.childbasket.domain.child.dto.ChildResponseDto;
import ru.childbasket.domain.parent.Parent;
import ru.childbasket.domain.parent.ParentRepository;
import ru.childbasket.domain.parent.exceptions.ParentNotExistsException;

import java.util.List;
import java.util.stream.Collectors;

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

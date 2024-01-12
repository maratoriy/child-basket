package ru.childmarket.childmarket.domain.child;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.childmarket.childmarket.domain.child.ParentChildrenService;
import ru.childmarket.childmarket.domain.child.dto.ChildCreateDto;
import ru.childmarket.childmarket.domain.child.dto.ChildResponseDto;

@RestController
@RequestMapping(value = {"/api/parent/children"},
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ParentChildrenController {
    private final ParentChildrenService parentChildrenService;

    @GetMapping("/all")
    public ResponseEntity<List<ChildResponseDto>> getChildren(@AuthenticationPrincipal UserDetails userDetails) {
        final List<ChildResponseDto> childResponseDtoList = parentChildrenService.getChildren(userDetails.getUsername());

        return ResponseEntity.ok().body(childResponseDtoList);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addChild(@RequestBody @Valid ChildCreateDto childCreateDto, @AuthenticationPrincipal UserDetails userDetails) {
        parentChildrenService.addChild(userDetails.getUsername(), childCreateDto);

        return ResponseEntity.ok().build();
    }

}

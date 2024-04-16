package ru.childbasket.domain.child;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.childbasket.domain.child.dto.ChildCreateDto;
import ru.childbasket.domain.child.dto.ChildResponseDto;

import java.util.List;

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

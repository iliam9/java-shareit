package ru.practicum.shareit.request;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.request.dto.ItemRequestDto;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
@Validated
public class ItemRequestController {

    private final ItemRequestClient itemRequestClient;

    private final String header = "X-Sharer-User-Id";

    @PostMapping
    public ResponseEntity<Object> saveItemRequest(@RequestHeader(header) @NotNull final Integer userId,
                                                  @RequestBody final ItemRequestDto itemRequestDto) {
        return itemRequestClient.saveItemRequest(userId, itemRequestDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllByUser(@RequestHeader(header) final Integer userId) {
        return itemRequestClient.getAllByUser(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAll(@RequestHeader(header) final Integer userId) {
        return itemRequestClient.getAll(userId);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Object> getById(@PathVariable final Integer requestId) {
        return itemRequestClient.getById(requestId);
    }
}


package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.group.CreateGroup;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Validated
public class ItemController {

    private final ItemClient itemClient;

    private final String header = "X-Sharer-User-Id";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> save(@RequestHeader(header) @NotNull final Integer ownerId,
                                       @Validated(CreateGroup.class) @RequestBody final ItemDto itemDto) {
        return itemClient.save(ownerId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> update(@RequestHeader(header) @NotNull final Integer ownerId,
                          @PathVariable @Positive final Integer itemId,
                          @RequestBody final ItemDto itemDto) {
        return itemClient.update(ownerId, itemId, itemDto);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> findById(@RequestHeader(header) @NotNull final Integer ownerId,
                                 @PathVariable @Positive final Integer itemId) {
        return itemClient.findById(ownerId, itemId);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive final Integer itemId) {
        itemClient.delete(itemId);
    }

    @GetMapping
    public ResponseEntity<Object> getItemsByOwnerId(@RequestHeader(header) @NotNull final Integer ownerId) {
        return itemClient.getItemsByOwnerId(ownerId);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestHeader(header) @Positive final Integer userId,
                                         @RequestParam final String text) {
        return itemClient.search(userId, text);
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> saveComment(@RequestHeader(header) @NotNull final Integer userId,
                                  @PathVariable @NotNull final Integer itemId,
                                  @Valid @RequestBody final CommentDto commentDto) {
        return itemClient.saveComment(userId, itemId, commentDto);
    }
}



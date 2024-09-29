package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.group.CreateGroup;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemResponse;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;


@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemController {

    ItemService itemService;

    private static final String HEADER = "X-Sharer-User-Id";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto save(@RequestHeader(HEADER) @NotNull final Integer ownerId,
                        @Validated(CreateGroup.class) @RequestBody final ItemDto itemDto) {
        return itemService.save(ownerId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestHeader(HEADER) @NotNull final Integer ownerId,
                          @PathVariable @Positive final Integer itemId,
                          @RequestBody final ItemDto itemDto) {
        return itemService.update(ownerId, itemId, itemDto);
    }

    @GetMapping("/{itemId}")
    public ItemResponse findById(@RequestHeader(HEADER) @NotNull final Integer ownerId,
                                 @PathVariable @Positive final Integer itemId) {
        return itemService.findById(ownerId, itemId);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive final Integer itemId) {
        itemService.delete(itemId);
    }

    @GetMapping
    public List<ItemDto> getItemsByOwnerId(@RequestHeader(HEADER) @NotNull final Integer ownerId) {
        return itemService.getItemsByOwnerId(ownerId);
    }

    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam final String text) {
        return itemService.search(text);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto saveComment(@RequestHeader(HEADER) @NotNull final Integer userId,
                                  @PathVariable @NotNull final Integer itemId,
                                  @Valid @RequestBody final CommentDto commentDto) {
        return itemService.saveComment(userId, itemId, commentDto);
    }
}


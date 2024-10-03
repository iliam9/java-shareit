package ru.practicum.shareit.request.dto;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@Component
public class ItemRequestMapper {

    public ItemRequest toItemRequest(final ItemRequestDto itemRequestDto, final User user) {

        final ItemRequest itemRequest = new ItemRequest();

        itemRequest.setDescription(itemRequestDto.getDescription());
        itemRequest.setCreated(LocalDateTime.now());
        itemRequest.setRequestor(user);

        return itemRequest;
    }

    public ItemRequestResponseDto toItemRequestResponceDto(final ItemRequest itemRequest) {

        final ItemRequestResponseDto itemRequestResponseDto = new ItemRequestResponseDto();

        itemRequestResponseDto.setId(itemRequest.getId());
        itemRequestResponseDto.setCreated(itemRequest.getCreated());
        itemRequestResponseDto.setDescription(itemRequest.getDescription());

        return itemRequestResponseDto;
    }
}

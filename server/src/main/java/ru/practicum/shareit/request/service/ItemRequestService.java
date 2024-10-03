package ru.practicum.shareit.request.service;

import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestResponseDto;

import java.util.List;

public interface ItemRequestService {

    ItemRequestResponseDto saveItemRequest(final Integer userId, final ItemRequestDto itemRequestDto);

    List<ItemRequestResponseDto> getAllByUser(final Integer userId);

    List<ItemRequestResponseDto> getAll(final Integer userId);

    ItemRequestResponseDto getById(final Integer requestId);

}

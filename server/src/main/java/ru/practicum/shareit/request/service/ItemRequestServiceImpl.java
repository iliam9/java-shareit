package ru.practicum.shareit.request.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDtoResponseForIR;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.ItemRequestMapper;
import ru.practicum.shareit.request.dto.ItemRequestResponseDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.ItemRequestRepository;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemRequestServiceImpl implements ItemRequestService {

    ItemRequestRepository itemRequestRepository;

    ItemRequestMapper itemRequestMapper;

    UserRepository userRepository;

    UserMapper userMapper;

    ItemRepository itemRepository;

    ItemMapper itemMapper;

    final Sort sort = Sort.by(Sort.Direction.DESC, "created");

    @Override
    @Transactional
    public ItemRequestResponseDto saveItemRequest(Integer userId, ItemRequestDto itemRequestDto) {
        log.info("Создание нового запроса");
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователя с id = {} нет." + userId));
        final ItemRequest itemRequest = itemRequestRepository.save(itemRequestMapper.toItemRequest(itemRequestDto,
                user));
        log.info("Успешно создан запрос на вещь");
        final ItemRequestResponseDto itemRequestResponseDto =
                itemRequestMapper.toItemRequestResponceDto(itemRequest);
        itemRequestResponseDto.setRequestor(userMapper.toUserDto(user));
        return itemRequestResponseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemRequestResponseDto> getAllByUser(Integer userId) {
        log.info("Запрос на получение всех запросов пользователя с id = {} и все ответы на них", userId);
        final List<ItemRequest> itemRequests = itemRequestRepository.findAllByRequestorId(userId, sort);
        List<ItemRequestResponseDto> list = new ArrayList<>();
        for (ItemRequest itemRequest : itemRequests) {
            final ItemRequestResponseDto itemRequestResponseDto =
                    itemRequestMapper.toItemRequestResponceDto(itemRequest);
            final List<ItemDtoResponseForIR> items = itemRepository.findAllByRequest(itemRequest)
                    .stream().map(itemMapper::toItemDtoResponceForIR).toList();
            itemRequestResponseDto.setItems(items);
            itemRequestResponseDto.setRequestor(userMapper.toUserDto(itemRequest.getRequestor()));
            list.add(itemRequestResponseDto);
        }
        log.info("Все запросы пользователя с id = {} с предложенными вещами", userId);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemRequestResponseDto> getAll(Integer userId) {
        log.info("Запрос на получение всех запросов, кроме тех что сделал пользователь с id = {}",
                userId);
        final List<ItemRequest> itemRequests = itemRequestRepository.findAllByRequestorIdNot(userId, sort);
        log.info("Получены все запросы кроме запросов пользователя с id = {}", userId);
        final List<ItemRequestResponseDto> list = new ArrayList<>();
        for (ItemRequest itemRequest : itemRequests) {
           final ItemRequestResponseDto itemRequestResponseDto =
                   itemRequestMapper.toItemRequestResponceDto(itemRequest);
           itemRequestResponseDto.setRequestor(userMapper.toUserDto(itemRequest.getRequestor()));
           list.add(itemRequestResponseDto);
       }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public ItemRequestResponseDto getById(Integer requestId) {
        log.info("Запрос на получение запроса с id = {} и все ответы на него", requestId);
        final ItemRequest itemRequest = itemRequestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Запроса с id = {} нет." + requestId));
        final ItemRequestResponseDto itemRequestResponseDto =
                itemRequestMapper.toItemRequestResponceDto(itemRequest);
        final List<ItemDtoResponseForIR> items = itemRepository.findAllByRequest(itemRequest)
                .stream().map(itemMapper::toItemDtoResponceForIR).toList();
        itemRequestResponseDto.setItems(items);
        itemRequestResponseDto.setRequestor(userMapper.toUserDto(itemRequest.getRequestor()));
        log.info("Получен запрос с id = {} и все ответы на него", requestId);
        return itemRequestResponseDto;
    }
}

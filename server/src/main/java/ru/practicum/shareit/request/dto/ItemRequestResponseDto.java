package ru.practicum.shareit.request.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDtoResponseForIR;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Component
@EqualsAndHashCode(exclude = {"id"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestResponseDto {

    Integer id;

    String description;

    UserDto requestor;

    LocalDateTime created;

    List<ItemDtoResponseForIR> items;

}


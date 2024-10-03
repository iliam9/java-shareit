package ru.practicum.shareit.item.dto;

import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Setter
@Component
public class ItemMapper {

    public Item toItem(final User owner, final ItemDto itemDto) {

        final Item item = new Item();

        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setOwner(owner);
        item.setAvailable(itemDto.getAvailable());

        return item;
    }

    public ItemDto toItemDto(final Item item) {

        final ItemDto itemDto = new ItemDto();

        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setAvailable(item.getAvailable());

        return itemDto;
    }

    public ItemResponse toItemResponce(final Item item, List<CommentDto> comments) {

        final ItemResponse itemResponse = new ItemResponse();

        itemResponse.setId(item.getId());
        itemResponse.setName(item.getName());
        itemResponse.setDescription(item.getDescription());
        itemResponse.setAvailable(item.getAvailable());
        itemResponse.setComments(comments);

        return itemResponse;
    }

    public ItemDtoResponseForIR toItemDtoResponceForIR(Item item) {

        final ItemDtoResponseForIR itemDtoResponseForIR = new ItemDtoResponseForIR();

        itemDtoResponseForIR.setItemId(item.getId());
        itemDtoResponseForIR.setOwnerId(item.getOwner().getId());
        itemDtoResponseForIR.setName(item.getName());

        return itemDtoResponseForIR;
    }
}

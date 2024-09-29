package ru.practicum.shareit.booking.dto;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatus;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;


@Component
public class BookingMapper {

    public Booking toBooking(final BookingRequest bookingRequest, final User user, final Item item) {

        final Booking booking = new Booking();

        booking.setStart(bookingRequest.getStart());
        booking.setEnd(bookingRequest.getEnd());
        booking.setItem(item);
        booking.setBooker(user);
        booking.setStatus(BookingStatus.WAITING);

        return booking;
    }

    public BookingResponse toBookingResponse(final Booking booking, final UserDto userDto,
                                             final ItemDto itemDto) {

        final BookingResponse bookingResponse = new BookingResponse();

        bookingResponse.setId(booking.getId());
        bookingResponse.setStart(booking.getStart());
        bookingResponse.setEnd(booking.getEnd());
        bookingResponse.setItem(itemDto);
        bookingResponse.setBooker(userDto);
        bookingResponse.setStatus(booking.getStatus());

        return bookingResponse;
    }
}

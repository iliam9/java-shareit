package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingRequest;
import ru.practicum.shareit.booking.dto.BookingResponse;

import java.util.List;

public interface BookingService {

    BookingResponse saveRequest(final BookingRequest bookingRequest, final Integer userId);

    BookingResponse approved(final Integer ownerId, final Integer bookingId, final boolean approved);

    BookingResponse findById(final Integer userId, final Integer bookingId);

    List<BookingResponse> findAllByUserId(final Integer userId, final String state);

    List<BookingResponse> findAllByOwnerId(final Integer ownerId, final String state);
}

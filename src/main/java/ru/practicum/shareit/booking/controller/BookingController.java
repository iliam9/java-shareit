package ru.practicum.shareit.booking.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingRequest;
import ru.practicum.shareit.booking.dto.BookingResponse;
import ru.practicum.shareit.booking.service.BookingService;

import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Validated
public class BookingController {

    private final BookingService bookingService;

    private final String HEADER = "X-Sharer-User-Id";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse saveRequest(@RequestHeader(HEADER) @NotNull final Integer userId,
                                       @RequestBody final BookingRequest bookingRequest) {
        return bookingService.saveRequest(bookingRequest, userId);

    }

    @PatchMapping("/{bookingId}")
    public BookingResponse approved(@RequestHeader(HEADER) @NotNull final Integer ownerId,
                                    @PathVariable final Integer bookingId,
                                    @RequestParam final boolean approved) {
        return bookingService.approved(ownerId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingResponse findById(@RequestHeader(HEADER) @NotNull final Integer userId,
                                    @PathVariable final Integer bookingId) {
        return bookingService.findById(userId, bookingId);
    }

    @GetMapping
    public List<BookingResponse> findAllByUserId(@RequestHeader(HEADER) @NotNull final Integer userId,
                                                 @RequestParam(defaultValue = "all") final String state) {
        return bookingService.findAllByUserId(userId, state);
    }

    @GetMapping("/owner")
    public List<BookingResponse> findAllByOwnerId(@RequestHeader(HEADER) @NotNull final Integer ownerId,
                                                  @RequestParam(defaultValue = "all") final String state) {
        return bookingService.findAllByOwnerId(ownerId, state);
    }

}
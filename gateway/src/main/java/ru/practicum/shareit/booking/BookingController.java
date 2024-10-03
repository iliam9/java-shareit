package ru.practicum.shareit.booking;

import org.springframework.http.HttpStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.booking.dto.BookingRequest;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Validated
public class BookingController {

    private final BookingClient bookingClient;

    private final String header = "X-Sharer-User-Id";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> saveRequest(@RequestHeader(header) @NotNull final Integer userId,
                                              @RequestBody final BookingRequest bookingRequest) {
        return bookingClient.saveRequest(userId, bookingRequest);

    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> approved(@RequestHeader(header) @NotNull final Integer ownerId,
                                    @PathVariable final Integer bookingId,
                                    @RequestParam final boolean approved) {
        return bookingClient.approved(ownerId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> findById(@RequestHeader(header) @NotNull final Integer userId,
                                    @PathVariable final Integer bookingId) {
        return bookingClient.findById(userId, bookingId);
    }

    @GetMapping
    public ResponseEntity<Object> findAllByUserId(@RequestHeader(header) @NotNull final Integer userId,
                                                 @RequestParam(defaultValue = "all") final String state) {
        return bookingClient.findAllByUserId(userId, state);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> findAllByOwnerId(@RequestHeader(header) @NotNull final Integer ownerId,
                                                   @RequestParam(defaultValue = "all") final String state) {
        return bookingClient.findAllByOwnerId(ownerId, state);
    }

}

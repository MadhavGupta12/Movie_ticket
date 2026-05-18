package com.moviebooking.service;

import com.moviebooking.model.Booking;
import com.moviebooking.model.Seat;
import com.moviebooking.model.User;
import com.moviebooking.repository.BookingRepository;
import com.moviebooking.repository.SeatRepository;
import com.moviebooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {
  private final BookingRepository bookingRepository;
  private final SeatRepository seatRepository;
  private final UserRepository userRepository;

  public List<Booking> getBookingsByUser(User user) {
    return bookingRepository.findByUser(user);
  }

  public Optional<User> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Transactional
  public Booking reserveSeat(User user, Seat seat) {
    if (!seat.isAvailable()) {
      throw new IllegalStateException("Seat already booked");
    }
    seat.setAvailable(false);
    seatRepository.save(seat);

    Booking booking = Booking.builder()
      .user(user)
      .seat(seat)
      .bookingTime(LocalDateTime.now())
      .confirmed(true)
      .build();

    return bookingRepository.save(booking);
  }
}

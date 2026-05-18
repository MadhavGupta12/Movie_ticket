package com.moviebooking.controller;

import com.moviebooking.model.Booking;
import com.moviebooking.model.Movie;
import com.moviebooking.model.Seat;
import com.moviebooking.model.User;
import com.moviebooking.repository.MovieRepository;
import com.moviebooking.repository.SeatRepository;
import com.moviebooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
  private final BookingService bookingService;
  private final SeatRepository seatRepository;
  private final MovieRepository movieRepository;

  @GetMapping("/movies")
  public List<Movie> listMovies() {
    return movieRepository.findAll();
  }

  @GetMapping("/movies/{movieId}/seats")
  public List<Seat> availableSeats(@PathVariable Long movieId) {
    Movie movie = movieRepository.findById(movieId)
      .orElseThrow(() -> new IllegalArgumentException("Invalid movie id"));
    return seatRepository.findByMovieAndAvailableTrue(movie);
  }

  @PostMapping("/reserve")
  public ResponseEntity<Booking> reserveSeat(
      @RequestParam String email,
      @RequestParam Long seatId) {

    User user = bookingService.findUserByEmail(email)
      .orElseThrow(() -> new IllegalArgumentException("User not found"));
    Seat seat = seatRepository.findById(seatId)
      .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
    Booking booking = bookingService.reserveSeat(user, seat);
    return ResponseEntity.ok(booking);
  }
}

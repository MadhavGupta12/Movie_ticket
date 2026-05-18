package com.moviebooking.controller;

import com.moviebooking.model.Booking;
import com.moviebooking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
  private final BookingRepository bookingRepository;

  @GetMapping("/reports/bookings")
  public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
  }
}

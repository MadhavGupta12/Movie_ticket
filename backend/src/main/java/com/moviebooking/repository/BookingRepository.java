package com.moviebooking.repository;

import com.moviebooking.model.Booking;
import com.moviebooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
  List<Booking> findByUser(User user);
}

package com.moviebooking.repository;

import com.moviebooking.model.Seat;
import com.moviebooking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
  List<Seat> findByMovieAndAvailableTrue(Movie movie);
}

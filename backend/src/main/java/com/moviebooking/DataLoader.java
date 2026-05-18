package com.moviebooking;

import com.moviebooking.model.Movie;
import com.moviebooking.model.Seat;
import com.moviebooking.model.User;
import com.moviebooking.repository.MovieRepository;
import com.moviebooking.repository.SeatRepository;
import com.moviebooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
  private final UserRepository userRepository;
  private final MovieRepository movieRepository;
  private final SeatRepository seatRepository;

  @Override
  public void run(String... args) {
    if (userRepository.count() > 0) {
      return;
    }

    User admin = User.builder()
      .name("Admin User")
      .email("admin@movie.com")
      .password("admin123")
      .role("ADMIN")
      .build();

    User regular = User.builder()
      .name("Test User")
      .email("user@movie.com")
      .password("user123")
      .role("USER")
      .build();

    userRepository.save(admin);
    userRepository.save(regular);

    Movie movie = Movie.builder()
      .title("Spring Boot Premiere")
      .theater("Screen 1")
      .showTime("7:00 PM")
      .build();

    movieRepository.save(movie);

    List<Seat> seats = new ArrayList<>();
    for (int i = 1; i <= 20; i++) {
      seats.add(Seat.builder()
        .seatNumber("A" + i)
        .available(true)
        .movie(movie)
        .build());
    }
    seatRepository.saveAll(seats);
  }
}

import { Component, OnInit } from '@angular/core';
import { ApiService } from './api.service';
import { Movie, Seat } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Movie Ticket Booking';
  movies: Movie[] = [];
  availableSeats: Seat[] = [];
  selectedMovieId: number | null = null;
  selectedSeatId: number | null = null;
  bookingEmail = 'user@movie.com';
  registerName = '';
  registerEmail = '';
  registerPassword = '';
  message = '';

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.loadMovies();
  }

  loadMovies(): void {
    this.apiService.getMovies().subscribe({
      next: movies => this.movies = movies,
      error: () => this.message = 'Failed to load movies from the backend.'
    });
  }

  loadSeats(): void {
    if (!this.selectedMovieId) {
      this.availableSeats = [];
      return;
    }

    this.apiService.getAvailableSeats(this.selectedMovieId).subscribe({
      next: seats => {
        this.availableSeats = seats;
        this.selectedSeatId = null;
      },
      error: () => this.message = 'Unable to fetch available seats.'
    });
  }

  reserve(): void {
    if (!this.selectedSeatId || !this.bookingEmail) {
      this.message = 'Please select a seat and provide an email.';
      return;
    }

    this.apiService.reserveSeat(this.bookingEmail, this.selectedSeatId).subscribe({
      next: booking => {
        this.message = `Seat ${booking.seat.seatNumber} reserved successfully!`;
        this.loadSeats();
      },
      error: () => this.message = 'Seat reservation failed. Check the email and try again.'
    });
  }

  registerUser(): void {
    if (!this.registerName || !this.registerEmail || !this.registerPassword) {
      this.message = 'Please complete all registration fields.';
      return;
    }

    this.apiService.registerUser({
      name: this.registerName,
      email: this.registerEmail,
      password: this.registerPassword
    }).subscribe({
      next: () => {
        this.message = 'Registration successful. Use your email to reserve seats.';
        this.registerName = '';
        this.registerEmail = '';
        this.registerPassword = '';
      },
      error: () => this.message = 'Registration failed. Email may already be in use.'
    });
  }
}

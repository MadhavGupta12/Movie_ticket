import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Booking, Movie, Seat, User } from './models';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.baseUrl}/bookings/movies`);
  }

  getAvailableSeats(movieId: number): Observable<Seat[]> {
    return this.http.get<Seat[]>(`${this.baseUrl}/bookings/movies/${movieId}/seats`);
  }

  reserveSeat(email: string, seatId: number): Observable<Booking> {
    const params = new HttpParams().set('email', email).set('seatId', seatId.toString());
    return this.http.post<Booking>(`${this.baseUrl}/bookings/reserve`, null, { params });
  }

  registerUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/users/register`, user);
  }
}

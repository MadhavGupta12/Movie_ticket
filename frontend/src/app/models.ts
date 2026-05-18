export interface Movie {
  id: number;
  title: string;
  theater: string;
  showTime: string;
}

export interface Seat {
  id: number;
  seatNumber: string;
  available: boolean;
  movie: Movie;
}

export interface Booking {
  id: number;
  bookingTime: string;
  confirmed: boolean;
  user: {
    id: number;
    email: string;
    name: string;
  };
  seat: Seat;
}

export interface User {
  id?: number;
  name: string;
  email: string;
  password: string;
  role?: string;
}

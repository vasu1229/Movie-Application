package com.cg.movie.service;

import java.util.List;

import com.cg.movie.dto.MovieDto;
import com.cg.movie.entity.Movie;

public interface MovieService {
	
	public MovieDto addMovie(MovieDto moviedto);
	public Movie getMovieByName(String movieName);
	public Movie getMovieById(Integer movieId);
	public List<Movie> getAllMovies();
	public List<Movie> getMovieByDirectorName(String directorName);
	public String deleteMovieById(Integer movieId);
	public String updateMovie(Movie movie);

}

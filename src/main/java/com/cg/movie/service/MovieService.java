package com.cg.movie.service;

import java.util.List;

import com.cg.movie.dto.MovieDto;
import com.cg.movie.entity.Movie;

public interface MovieService {
	
	public MovieDto addMovie(MovieDto moviedto);
	public MovieDto getMovieByName(String movieName);
	public MovieDto getMovieById(Integer movieId);
	public List<MovieDto> getAllMovies();
	public List<MovieDto> getMovieByDirectorName(String directorName);
	public String deleteMovieById(Integer movieId);
	public MovieDto updateMovie(MovieDto moviedto);

}

package com.cg.movie.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.movie.dto.MovieDto;
import com.cg.movie.entity.Movie;
import com.cg.movie.exception.MovieException;
import com.cg.movie.mapper.MovieMapper;
import com.cg.movie.service.MovieService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	MovieService movieService;

	Logger logger = LoggerFactory.getLogger(MovieController.class);
	long elapsedTime;

	@PostMapping("/add")
	public ResponseEntity<MovieDto> addMovie(@RequestBody @Valid MovieDto moviedto) throws MovieException {
		long startTime = System.currentTimeMillis();
		logger.info("MovieController- addMovie Started ");
	
		MovieDto result = movieService.addMovie(moviedto);
		elapsedTime = System.currentTimeMillis() - startTime;
		logger.info("MovieController- addMovie Ended");
		logger.info("Time Taken: " + elapsedTime + " ms");
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@GetMapping("/getName/{movieName}")
	public ResponseEntity<Movie> getMovieByName(@PathVariable String movieName) throws MovieException {
		long startTime = System.currentTimeMillis();
		logger.info("MovieController- getMovieByName Started");
		Movie result = movieService.getMovieByName(movieName);
		elapsedTime = System.currentTimeMillis() - startTime;
		logger.info("MovieController- getMovieByName Ended");
		logger.info("Time Taken: " + elapsedTime + " ms");
		return new ResponseEntity<Movie>(result, HttpStatus.OK);
	}

	@GetMapping("/getId/{movieId}")
	public ResponseEntity<Movie> getMovieById(@PathVariable Integer movieId) throws MovieException {
		long startTime = System.currentTimeMillis();
		logger.info("MovieController- getMovieById Started");
		Movie result = movieService.getMovieById(movieId);
		elapsedTime = System.currentTimeMillis() - startTime;
		logger.info("MovieController- getMovieById Ended");
		logger.info("Time Taken: " + elapsedTime + " ms");
		return new ResponseEntity<Movie>(result, HttpStatus.OK);
	}

	@GetMapping("/getAllMovies")
	public ResponseEntity<List<Movie>> getAllMovies() throws MovieException {
		long startTime = System.currentTimeMillis();
		logger.info("MovieController- getAllMovies Started");
		List<Movie> result = movieService.getAllMovies();
//		List<Movie> result = movieRepository.getMoviesInAscendingOrder();
		elapsedTime = System.currentTimeMillis() - startTime;
		logger.info("MovieController- getAllMovies Ended");
		logger.info("Time Taken: " + elapsedTime + " ms");
		return new ResponseEntity<List<Movie>>(result, HttpStatus.OK);

	}

	@GetMapping("/getByDirector/{directorName}")
	public ResponseEntity<List<Movie>> getMovieByDirectorName(@PathVariable String directorName) throws MovieException {
		long startTime = System.currentTimeMillis();
		logger.info("MovieController- getMovieByDirector Started");
		List<Movie> result = movieService.getMovieByDirectorName(directorName);
		elapsedTime = System.currentTimeMillis() - startTime;
		logger.info("MovieController- getMovieByDirector Ended");
		logger.info("Time Taken: " + elapsedTime + " ms");
		return new ResponseEntity<List<Movie>>(result, HttpStatus.OK);
	}

	@PutMapping("/Update")
	public ResponseEntity<String> updateMovie(@RequestBody Movie movie) throws MovieException {
		long startTime = System.currentTimeMillis();
		logger.info("MovieController- updateMovie Started");
		String result = movieService.updateMovie(movie);
		elapsedTime = System.currentTimeMillis() - startTime;
		logger.info("MovieController- updateMovie Ended");
		logger.info("Time Taken: " + elapsedTime + " ms");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{movieId}")
	public ResponseEntity<String> deleteMovieById(@PathVariable Integer movieId) throws MovieException {
		long startTime = System.currentTimeMillis();
		logger.info("MovieController- deleteMovieById Started");
		String result = movieService.deleteMovieById(movieId);
		elapsedTime = System.currentTimeMillis() - startTime;
		logger.info("MovieController- deleteMovieById Ended");
		logger.info("Time Taken: " + elapsedTime + " ms");
		return new ResponseEntity<>(result, HttpStatus.OK);

	}


}

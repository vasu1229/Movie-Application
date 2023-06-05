package com.cg.movie.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movie.dao.MovieRepository;
import com.cg.movie.dto.MovieDto;
import com.cg.movie.entity.Movie;
import com.cg.movie.exception.MovieException;
import com.cg.movie.mapper.MovieMapper;
import com.cg.movie.service.MovieService;
import com.cg.movie.util.Constants;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;
	@Autowired
	MovieMapper movieMapper;
	Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

	@Override
	public MovieDto addMovie(MovieDto moviedto) {

		logger.info("MovieService- addMovie Started");
		
		logger.info("MovieMapper dtoToEntity- Request Mapper Started");
		Movie movie = movieMapper.dtoToModel(moviedto);
		logger.info("MovieMapper dtoToEntity- Request Mapper Ended " +movie);
		
		logger.info("MovieMapper EntityToDto- Response Mapper Started");
		MovieDto resultDto = movieMapper.modelToDto(movieRepository.save(movie));
		logger.info("MovieMapper EntityToDto- Response Mapper Ended " +resultDto);

		if (resultDto == null || resultDto.getMovieId() == null) {
			logger.error("Exception Occured");
			throw new MovieException(Constants.FAILED);
		}
		logger.info(Constants.ADD + movie.toString());

		logger.info("MovieService-addMovie Ended");
		return resultDto;

	}

	@Override
	public Movie getMovieByName(String movieName) {
		logger.info("Microservice- getMovieByName Started");
		Movie result = movieRepository.findByMovieName(movieName);
		logger.info("Movie record fetched successfully:" + result.toString());
		if (result == null || result.getMovieId() == null) {
			logger.error("Excepiton occurs");
			throw new MovieException(Constants.NOT_FOUND);
		}
		logger.info("Microservice- getMovieByName Ended");
		return result;
	}

	@Override
	public Movie getMovieById(Integer movieId) {
		logger.info("Microservice- getMovieById Started");
		Movie result = movieRepository.findById(movieId).get();
		logger.info("Movie record fetched successfully:" + result.toString());
		if (result == null || result.getMovieId() == null) {
			logger.error("Exception occurs");
			throw new MovieException(Constants.NOT_FOUND);
		}
		logger.info("Microservice- getMovieById Ended");
		return result;
	}

	@Override
	public List<Movie> getAllMovies() {
		logger.info("Microservice- getAllMovie Started");
		List<Movie> result = movieRepository.findAll();
		logger.info("Movie Records Fetched Successfully: " + result.toString());
		if (result.isEmpty() || result == null) {
			logger.error("Exception Occured");
			throw new MovieException(Constants.NOT_FOUND);
		}
		logger.info("MovieService-getAllMovies Ended");
		return result;
	}

	@Override
	public List<Movie> getMovieByDirectorName(String directorName) {
		logger.info("Microservice- getMovieByDirector Started");
		List<Movie> result = movieRepository.findByDirectorName(directorName);
		logger.info("Movie record fetched successfully:" + result.toString());
		if (result.isEmpty() || result == null) {
			logger.error("Exception Occured");
			throw new MovieException(Constants.NOT_FOUND);
		}
		logger.info("MovieService-getMovieByDirector Ended");
		return result;
	}

		
	@Override
	public String updateMovie(Movie movie) {
		logger.info("Microservice- updateMovie Started");

		Movie movieById = getMovieById(movie.getMovieId());

		if (movieById == null || movieById.getMovieId() == null) {
			logger.error("Exception occurs");
			throw new MovieException(Constants.NOT_FOUND);
		}
		Movie result = movieRepository.save(movie);

		logger.info("Movie Updated Successfully:" + result.toString());
		logger.info("Microservice- updateMovie Ended");
		return Constants.UPDATE+result.getMovieId();
	}



	@Override
	public String deleteMovieById(Integer movieId) {
		logger.info("Microservice- deleteMovieById Started");
		Movie movieById = getMovieById(movieId);
		if (movieById == null || movieById.getMovieId() == null) {
			logger.error("Exception Occured");
			throw new MovieException(Constants.NOT_FOUND);
		}
		movieRepository.deleteById(movieId);
		logger.info("Movie Record Deleted Successfully: " + movieId);
		logger.info("MovieService-deleteMovie Ended");

		return Constants.DELETE + movieId;
	}

}

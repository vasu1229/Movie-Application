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
		logger.info("MovieMapper dtoToEntity- Request Mapper Ended " + movie);

		logger.info("MovieMapper EntityToDto- Response Mapper Started");
		MovieDto resultDto = movieMapper.modelToDto(movieRepository.save(movie));
		logger.info("MovieMapper EntityToDto- Response Mapper Ended " + resultDto);

		if (resultDto == null || resultDto.getMovieId() == null) {
			logger.error("Exception Occured");
			throw new MovieException(Constants.FAILED);
		}
		logger.info(Constants.ADD + movie.toString());

		logger.info("MovieService-addMovie Ended");
		return resultDto;

	}

	@Override
	public MovieDto getMovieByName(String movieName) {
		logger.info("Microservice- getMovieByName Started");

		logger.info("MovieMapper EntityToDto- Response Mapper Started");
		MovieDto resultDto = movieMapper.modelToDto(movieRepository.findByMovieName(movieName));
		logger.info("MovieMapper EntityToDto- Response Mapper Ended " + resultDto);

		logger.info("Movie record fetched successfully:" + resultDto.toString());
		if (resultDto == null || resultDto.getMovieId() == null) {
			logger.error("Excepiton occurs");
			throw new MovieException(Constants.NOT_FOUND);
		}
		logger.info("Microservice- getMovieByName Ended");
		return resultDto;
	}

	@Override
	public MovieDto getMovieById(Integer movieId) {
		logger.info("Microservice- getMovieById Started");

		logger.info("MovieMapper EntityToDto- Response Mapper Started");
		MovieDto resultDto = movieMapper.modelToDto(movieRepository.findById(movieId).get());
		logger.info("MovieMapper EntityToDto- Response Mapper Ended " + resultDto);

		logger.info("Movie record fetched successfully:" + resultDto.toString());
		if (resultDto == null || resultDto.getMovieId() == null) {
			logger.error("Exception occurs");
			throw new MovieException(Constants.NOT_FOUND);
		}
		logger.info("Microservice- getMovieById Ended");
		return resultDto;
	}

	@Override
	public List<MovieDto> getAllMovies() {
		logger.info("Microservice- getAllMovie Started");

		List<Movie> result = movieRepository.findAll();
		logger.info("Movie Records Fetched Successfully: " + result.toString());
		logger.info("MovieMapper EntityToDto- Response Mapper Started");

		List<MovieDto> resultDto = movieMapper.listModelToDto(result);
		logger.info("MovieMapper EntityToDto- Response Mapper Ended " + resultDto);

		if (resultDto.isEmpty() || resultDto == null) {
			logger.error("Exception Occured");
			throw new MovieException(Constants.NOT_FOUND);
		}
		logger.info("MovieService-getAllMovies Ended");
		return resultDto;
	}

	@Override
	public List<MovieDto> getMovieByDirectorName(String directorName) {
		logger.info("Microservice- getMovieByDirector Started");
		List<Movie> result = movieRepository.findByDirectorName(directorName);
		logger.info("Movie record fetched successfully:" + result.toString());

		logger.info("MovieMapper EntityToDto- Response Mapper Started");

		List<MovieDto> resultDto = movieMapper.listModelToDto(result);
		logger.info("MovieMapper EntityToDto- Response Mapper Ended " + resultDto);

		if (resultDto.isEmpty() || resultDto == null) {
			logger.error("Exception Occured");
			throw new MovieException(Constants.NOT_FOUND);
		}
		logger.info("MovieService-getMovieByDirector Ended");
		return resultDto;
	}

	@Override
	public MovieDto updateMovie(MovieDto moviedto) {
		logger.info("Microservice- updateMovie Started");

		MovieDto movieById = getMovieById(moviedto.getMovieId());

		if (movieById == null || movieById.getMovieId() == null) {
			logger.error("Exception occurs");
			throw new MovieException(Constants.NOT_FOUND);
		}

		logger.info("MovieMapper dtoToEntity- Request Mapper Started");
		Movie movie = movieMapper.dtoToModel(moviedto);
		logger.info("MovieMapper dtoToEntity- Request Mapper Ended " + movie);

		logger.info("MovieMapper EntityToDto- Response Mapper Started");
		MovieDto resultDto = movieMapper.modelToDto(movieRepository.save(movie));
		logger.info("MovieMapper EntityToDto- Response Mapper Ended " + resultDto);

		logger.info("Movie Updated Successfully:" + resultDto.toString());
		logger.info("Microservice- updateMovie Ended");
		return resultDto;
	}

	@Override
	public String deleteMovieById(Integer movieId) {
		logger.info("Microservice- deleteMovieById Started");
		MovieDto movieById = getMovieById(movieId);
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

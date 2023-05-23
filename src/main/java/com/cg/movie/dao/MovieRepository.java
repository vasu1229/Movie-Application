package com.cg.movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.movie.dto.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

//	@Query (value= "select m from movie m order by m.movieName")
//	public List<Movie> getMoviesInAscendingOrder();

	public Movie findByMovieName(String movieName);

	public List<Movie> findByDirectorName(String directorName);

}

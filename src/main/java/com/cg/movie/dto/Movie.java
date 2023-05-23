package com.cg.movie.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer movieId;
	@NotNull (message = "Movie name cannot be null ")
	@NotBlank(message = "Movie name cannot be blank")
	private String movieName;
	private String language;
	@Min(value = 1990, message = "Release year should be after 1990")
	@Max(value = 2023, message = "Release year should be before 2023")
	private Integer releaseYear;
	private String genre;
	private String directorName;

}

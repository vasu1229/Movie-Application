package com.cg.movie.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cg.movie.dto.MovieDto;
import com.cg.movie.entity.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {
	
	MovieMapper INSTANCE=Mappers.getMapper(MovieMapper.class);
	@Mapping(target ="director", source = "directorName")
	MovieDto modelToDto(Movie movie);
	
//	@InheritInverseConfiguration - it will apply the same mapping condition
	@InheritInverseConfiguration
	Movie dtoToModel(MovieDto moviedto);

}

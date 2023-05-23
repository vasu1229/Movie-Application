package com.cg.movie.exception;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExceptionDetails {
	
	private Date date;
	private String message;
	private String description;
	
	

}

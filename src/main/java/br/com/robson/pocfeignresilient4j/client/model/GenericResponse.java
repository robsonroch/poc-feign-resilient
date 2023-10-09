package br.com.robson.pocfeignresilient4j.client.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GenericResponse {
	
	  @JsonProperty("description")
	  private String description;

	  @JsonProperty("message")
	  private String message;

	  @JsonProperty("timestamp")
	  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
	  private Date timestamp;

}

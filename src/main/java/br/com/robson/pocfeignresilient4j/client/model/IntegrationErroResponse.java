package br.com.robson.pocfeignresilient4j.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IntegrationErroResponse {
	
	private Integer statusCode;
	private String description;

}

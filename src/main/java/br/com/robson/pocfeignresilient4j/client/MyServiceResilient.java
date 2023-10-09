package br.com.robson.pocfeignresilient4j.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.robson.pocfeignresilient4j.client.model.GenericResponse;

public interface MyServiceResilient {
	@GetMapping("/resiliencia/exceptions{statusCodeErro}")
    GenericResponse getTesteStatusCodeErro(@PathVariable Integer statusCodeErro);
    
	@GetMapping("/resiliencia/retry{attempts}")
	GenericResponse getTesteRetryByAttempts(@PathVariable Integer attempts);
}
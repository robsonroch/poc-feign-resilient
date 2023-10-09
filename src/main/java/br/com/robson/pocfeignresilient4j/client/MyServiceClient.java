package br.com.robson.pocfeignresilient4j.client;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.robson.pocfeignresilient4j.client.model.ClienteRequest;
import br.com.robson.pocfeignresilient4j.client.model.ClienteResponse;

public interface MyServiceClient {
	@GetMapping("/clientes")
    List<ClienteResponse>  getData();
    
	@PostMapping("/clientes")
    ClienteResponse postData(@RequestBody ClienteRequest requestObject);
}
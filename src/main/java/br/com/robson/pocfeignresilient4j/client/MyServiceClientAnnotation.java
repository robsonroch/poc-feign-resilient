package br.com.robson.pocfeignresilient4j.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.robson.pocfeignresilient4j.client.model.ClienteRequest;
import br.com.robson.pocfeignresilient4j.client.model.ClienteResponse;

@FeignClient(name = "remote-service", fallbackFactory = MyServiceClientFallback.class)
public interface MyServiceClientAnnotation {
	@GetMapping("/clientes")
    List<ClienteResponse>  getData();
    
	@PostMapping("/clientes")
    ClienteResponse postData(@RequestBody ClienteRequest requestObject);
}
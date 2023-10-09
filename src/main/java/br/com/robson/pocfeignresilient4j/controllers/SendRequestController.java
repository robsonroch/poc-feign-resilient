package br.com.robson.pocfeignresilient4j.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.robson.pocfeignresilient4j.client.MyServiceClient;
import br.com.robson.pocfeignresilient4j.client.MyServiceResilient;
import br.com.robson.pocfeignresilient4j.client.model.ClienteResponse;
import br.com.robson.pocfeignresilient4j.client.model.GenericResponse;

@RestController
@RequestMapping("/send-request")
public class SendRequestController {
	
	@Autowired
	private MyServiceClient client;
	
	@Autowired
	private MyServiceResilient serviceResilient;
	
	@GetMapping("/list-client")
	public ResponseEntity<List<ClienteResponse>> getListClient(){
		List<ClienteResponse> data = client.getData();
		
		return ResponseEntity.ok(data);
	}
	
	@GetMapping("/retry/{tentativas}")
	public ResponseEntity<GenericResponse> testeRetry(@PathVariable("tentativas") Integer tentativas){
		System.out.println("retry");
		GenericResponse testeRetryByAttempts = serviceResilient.getTesteRetryByAttempts(tentativas);
		
		return ResponseEntity.ok(testeRetryByAttempts);
	}
	
	@GetMapping("/exceptions/{statusCode}")
	public ResponseEntity<GenericResponse> testeExceptions(@PathVariable("statusCode") Integer statusCode){
		
		System.out.println("exceptions");
		GenericResponse testeRetryByAttempts = serviceResilient.getTesteStatusCodeErro(statusCode);
		
		return ResponseEntity.ok(testeRetryByAttempts);
	}

}

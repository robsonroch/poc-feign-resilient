package br.com.robson.pocfeignresilient4j.client;

import java.util.List;

import br.com.robson.pocfeignresilient4j.client.model.ClienteRequest;
import br.com.robson.pocfeignresilient4j.client.model.ClienteResponse;
import feign.FeignException;
import feign.FeignException.FeignClientException;
import feign.FeignException.FeignServerException;

public class MyServiceClientFallback implements MyServiceClient {
	
    private Exception cause;

    public MyServiceClientFallback(Exception cause) {
        this.cause = cause;
    }

	@Override
	public List<ClienteResponse>  getData() {
        if (cause != null) {
            System.out.println(cause.getMessage());
        }
        if (cause instanceof FeignServerException) {
        	throw new RuntimeException("Deu ruim no servidor");
        }
        if (cause instanceof FeignClientException) {
        	throw new RuntimeException("Você fez algo de errado no request");
        }
        throw new RuntimeException("Error fetching account: " + cause.getMessage());
	}

	@Override
	public ClienteResponse postData(ClienteRequest requestObject) {
        if (cause != null) {
            System.out.println(cause.getMessage());
        }
        throw new RuntimeException("Error fetching account: " + cause.getMessage());
	}


}

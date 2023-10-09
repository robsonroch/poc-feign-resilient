package br.com.robson.pocfeignresilient4j.client;

import br.com.robson.pocfeignresilient4j.client.model.GenericResponse;
import feign.FeignException.FeignClientException;
import feign.FeignException.FeignServerException;

public class MyServiceResilientFallback implements MyServiceResilient {
	
    private Exception cause;

    public MyServiceResilientFallback(Exception cause) {
        this.cause = cause;
    }

	@Override
	public GenericResponse getTesteStatusCodeErro(Integer statusCodeErro) {
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
	public GenericResponse getTesteRetryByAttempts(Integer attempts) {
        if (cause != null) {
            System.out.println(cause.getMessage());
        }
        throw new RuntimeException("Error fetching account: " + cause.getMessage());
	}


}

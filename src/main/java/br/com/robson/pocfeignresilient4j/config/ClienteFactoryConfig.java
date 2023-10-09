package br.com.robson.pocfeignresilient4j.config;

import java.time.Duration;
import java.util.function.Function;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ClienteFactoryConfig<T, F extends T> {
	
	private final ObjectFactory<HttpMessageConverters> messageConverters;
	private final CircuitBreakerRegistry circuitBreakerRegistry;
	private final RateLimiterRegistry rateLimiterRegistry;
	private final RetryRegistry retryRegistry;
	private final BulkheadRegistry bulkheadRegistry;
		
	public <T> T getServiceCliente(Class<T> serviceClient, Function<Exception, ?> fallbackFactory, String SERVICE_NAME, String BaseUrl) {
   
        
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(SERVICE_NAME);
        RateLimiter rateLimiter = rateLimiterRegistry.rateLimiter(SERVICE_NAME);        
        Retry retry = retryRegistry.retry(SERVICE_NAME);

        Bulkhead bulkhead = bulkheadRegistry.bulkhead(SERVICE_NAME);
                                
        FeignDecorators decorators = FeignDecorators.builder()
        		.withRateLimiter(rateLimiter)
        		.withCircuitBreaker(circuitBreaker)
        		.withRetry(retry)
        		.withBulkhead(bulkhead)
                .withFallbackFactory(fallbackFactory)
                .build();
        
        T client = Resilience4jFeign.builder(decorators)
                .contract(new SpringMvcContract())
                .encoder(new SpringEncoder(messageConverters))
                .decoder(new SpringDecoder(messageConverters))
        		.target(serviceClient, BaseUrl);
        
        return client;
	}
	
}

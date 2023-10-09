package br.com.robson.pocfeignresilient4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.robson.pocfeignresilient4j.client.MyServiceClient;
import br.com.robson.pocfeignresilient4j.client.MyServiceClientFallback;
import br.com.robson.pocfeignresilient4j.client.MyServiceResilient;
import br.com.robson.pocfeignresilient4j.client.MyServiceResilientFallback;

@Configuration
public class BeanConfigs {

	@Bean
	public MyServiceClient getMyServiceClient(
			ClienteFactoryConfig<MyServiceClient, MyServiceClientFallback> clienteFactoryConfig) {

		MyServiceClient serviceCliente = clienteFactoryConfig.getServiceCliente(MyServiceClient.class,
				MyServiceClientFallback::new, "myservice", "http://localhost:8080/");

		return serviceCliente;
	}

	@Bean
	public MyServiceResilient getMyServiceResilient(
			ClienteFactoryConfig<MyServiceClient, MyServiceClientFallback> clienteFactoryConfig) {

		MyServiceResilient serviceCliente = clienteFactoryConfig.getServiceCliente(MyServiceResilient.class,
				MyServiceResilientFallback::new, "myresilient", "http://localhost:8080/");

		return serviceCliente;
	}
	
	@Bean
	@Profile("test1")
	public String test1() {
		return "test1";
	}
	
	@Bean
	@Profile("test2")
	public String test2() {
		return "test2";
	}

}

package br.com.robson.pocfeignresilient4j.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.robson.pocfeignresilient4j.client.MyServiceClient;
import br.com.robson.pocfeignresilient4j.client.MyServiceClientFallback;
import br.com.robson.pocfeignresilient4j.client.model.IntegrationErroResponse;
import br.com.robson.pocfeignresilient4j.exceptions.ResourceNotFoundException;
import feign.FeignException;
import feign.FeignException.FeignClientException;
import feign.Request;
import feign.Response;

@SpringBootTest
@AutoConfigureMockMvc
public class MyServiceClientFallBackTest {
	
	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    private MyServiceClient myFeignClient;
    
    @Autowired
    private MyServiceClientFallback fallback;
    
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testFallback() throws Exception {
        
    	@SuppressWarnings("deprecation")
		Request fakeRequest = Request.create(Request.HttpMethod.GET, "/send-request/list-client", Collections.emptyMap(), null, Charset.defaultCharset());
    	IntegrationErroResponse erro = IntegrationErroResponse.builder().statusCode(404).description("Error").build();
    	
    	String body = mapper.writeValueAsString(erro);
    	
    	ResourceNotFoundException notFound = new ResourceNotFoundException(body);
    	FeignClientException errorStatus2 = new FeignClientException(404, "deu ruim", fakeRequest, body.getBytes());
        when(myFeignClient.getData())
        .thenThrow(errorStatus2);

        mockMvc.perform(MockMvcRequestBuilders.get("/send-request/list-client")
        		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();
        
		/*
		 * FeignException.BadGateway("someMethod",
		 * FeignException.errorStatus("someMethod", feign.Response.builder()
		 * .status(404).request(feign.Request.create(feign.Request.HttpMethod.GET, "",
		 * Collections.emptyMap(), Request.Body.empty(), null)) .build()));
		 */

        
    }
    
	/*
	 * @Test public void testGetDataFallback() { // Criar uma exceção para simular
	 * um erro FeignException exception = FeignException.errorStatus("getData",
	 * feign.Response.builder() .status(500)
	 * .request(feign.Request.create(feign.Request.HttpMethod.GET, "",
	 * Collections.emptyList(), Request.Body.empty(), null)) .build());
	 * 
	 * // Criar uma instância do fallback MyServiceClientFallback fallback = new
	 * MyServiceClientFallback(exception);
	 * 
	 * // Chamar o método getData Exception runtimeException =
	 * assertThrows(RuntimeException.class, () -> { fallback.getData(); });
	 * 
	 * // Verificar se a exceção correta é lançada
	 * assertEquals("Deu ruim no servidor", runtimeException.getMessage()); }
	 * 
	 * @Test public void testPostDataFallback() { // Criar uma exceção para simular
	 * um erro FeignException exception = FeignException.errorStatus("postData",
	 * feign.Response.builder() .status(500)
	 * .request(feign.Request.create(feign.Request.HttpMethod.POST, "",
	 * Collections.emptyList(), Request.Body.empty(), null)) .build());
	 * 
	 * // Criar uma instância do fallback MyServiceClientFallback fallback = new
	 * MyServiceClientFallback(exception);
	 * 
	 * // Criar um objeto ClienteRequest ClienteRequest request = new
	 * ClienteRequest();
	 * 
	 * // Chamar o método postData Exception runtimeException =
	 * assertThrows(RuntimeException.class, () -> { fallback.postData(request); });
	 * 
	 * // Verificar se a exceção correta é lançada
	 * assertEquals("Error fetching account: " + exception.getMessage(),
	 * runtimeException.getMessage()); }
	 */
}

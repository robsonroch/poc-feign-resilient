package br.com.robson.pocfeignresilient4j.client.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest   {
  private String id;

  private String nome;

  private String sobrenome;

  private String email;

  private SexoEnum sexo;
  
  private String datanascimento;

  private List<EnderecoRequest> enderecos = new ArrayList<>();

  private List<TelefoneRequest> telefones = new ArrayList<>();
  
}


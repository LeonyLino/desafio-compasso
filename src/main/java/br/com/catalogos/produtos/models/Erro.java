package br.com.catalogos.produtos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Erro {

	private int statusCode;
	private String message;

}

package br.com.catalogos.produtos.models.dto;



import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoDTO {

	private Long id;
	@NotNull
	@Size(min = 3, max = 100)
	private String name;
	@NotNull
	@Size(min = 3, max = 100)
	private String description;
	@NotNull
	@DecimalMin("0.00")
	private float price;

}

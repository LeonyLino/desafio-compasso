package br.com.catalogos.produtos.models.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.catalogos.produtos.models.Produto;
import br.com.catalogos.produtos.models.dto.ProdutoDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProdutoDTOConverter implements Converter<Produto, ProdutoDTO> {

	@Override
	public ProdutoDTO convert(Produto produto) {
		return ProdutoDTO.builder().id(produto.getId()).name(produto.getName()).description(produto.getDescription())
				.price(produto.getPrice()).build();
	}

}

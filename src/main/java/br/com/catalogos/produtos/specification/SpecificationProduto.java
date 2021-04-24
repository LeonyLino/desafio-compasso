package br.com.catalogos.produtos.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.catalogos.produtos.models.dto.ProdutoDTO;

public class SpecificationProduto {

	public static Specification<ProdutoDTO> nome(String q) {
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + q + "%");
	}
}

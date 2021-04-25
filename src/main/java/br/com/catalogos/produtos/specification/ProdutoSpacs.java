package br.com.catalogos.produtos.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.com.catalogos.produtos.models.Produto;

public class ProdutoSpacs {

	public static Specification<Produto> nome(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		} else {
			return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
					.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");

		}
	}

	public static Specification<Produto> description(String description) {
		if (description == null || description.isEmpty()) {
			return null;
		} else {
			return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
					.like(criteriaBuilder.lower(root.get("description")), "%" + description.toLowerCase() + "%");

		}
	}

	public static Specification<Produto> minPrice(BigDecimal minPrice) {
		if (minPrice == null) {
			return null;
		} else {
			return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"),
					minPrice);
		}
	}

	public static Specification<Produto> maxPrice(BigDecimal maxPrice) {
		if (maxPrice == null) {
			return null;
		} else {
			return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"),
					maxPrice);
		}
	}

}
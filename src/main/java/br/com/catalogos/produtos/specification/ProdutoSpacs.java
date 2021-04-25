package br.com.catalogos.produtos.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import br.com.catalogos.produtos.models.Produto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProdutoSpacs implements Specification<Produto> {

	private static final long serialVersionUID = 1L;

	private String nameDescription;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;

	@Override
	public Predicate toPredicate(Root<Produto> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.hasText(nameDescription)) {
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),
					"%" + nameDescription.toLowerCase() + "%"));
		}

		if (minPrice != null && minPrice.compareTo(BigDecimal.ZERO) > 0) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
		}

		if (maxPrice != null && maxPrice.compareTo(BigDecimal.ZERO) > 0) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
		}

		query.distinct(true);

		return criteriaBuilder.and(predicates.toArray(new Predicate[1]));
	}

}
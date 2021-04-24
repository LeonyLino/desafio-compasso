package br.com.catalogos.produtos.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_PRODUTO")
@SequenceGenerator(name = "TB_PRODUTO_ID_SEQ", sequenceName = "TB_PRODUTO_ID_SEQ", initialValue = 1, allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TB_PRODUTO_ID_SEQ")
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false, length = 100)
	private String description;

	@Column(name = "PRICE", nullable = false)
	private float price;
}

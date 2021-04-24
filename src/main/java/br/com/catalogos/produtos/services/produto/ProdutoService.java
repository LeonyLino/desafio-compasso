package br.com.catalogos.produtos.services.produto;

import java.util.List;

import br.com.catalogos.produtos.models.dto.ProdutoDTO;

public interface ProdutoService {

	ProdutoDTO salvar(ProdutoDTO dto);
	
	ProdutoDTO editar(ProdutoDTO dto);
	
	ProdutoDTO buscar(Long id);
	
	List<ProdutoDTO> listarTodos();
	
	void deletar(Long id);
}

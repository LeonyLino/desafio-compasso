package br.com.catalogos.produtos.services.produto;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.catalogos.produtos.exceptions.EntidadeNaoEncontradaException;
import br.com.catalogos.produtos.models.Produto;
import br.com.catalogos.produtos.models.dto.ProdutoDTO;
import br.com.catalogos.produtos.models.dto.converter.ProdutoDTOConverter;
import br.com.catalogos.produtos.repository.ProdutoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

	private final ProdutoRepository pRepository;
	private final ProdutoDTOConverter pDTOConverter;

	@Override
	@Transactional
	public ProdutoDTO salvar(ProdutoDTO dto) {
		return pDTOConverter
				.convert(pRepository.save(new Produto(null, dto.getName(), dto.getDescription(), dto.getPrice())));
	}

	private Produto buscarPorId(Long id) {
		return pRepository.findById(id).orElseThrow(EntidadeNaoEncontradaException::new);
	}

	@Override
	@Transactional
	public ProdutoDTO editar(ProdutoDTO dto) {
		Produto produto = this.buscarPorId(dto.getId());
		produto.setName(dto.getName());
		produto.setDescription(dto.getDescription());
		produto.setPrice(dto.getPrice());

		return pDTOConverter.convert(pRepository.save(produto));

	}

	@Override
	public ProdutoDTO buscar(Long id) {
		return pDTOConverter.convert(this.buscarPorId(id));
	}

	@Override
	public List<ProdutoDTO> listarTodos() {
		return pRepository.findAll().stream().map(pDTOConverter::convert).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void deletar(Long id) {
		pRepository.deleteById(this.buscarPorId(id).getId());
	}

}

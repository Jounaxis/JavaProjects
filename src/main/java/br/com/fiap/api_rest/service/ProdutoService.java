package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.dto.ProdutoLista;
import br.com.fiap.api_rest.dto.ProdutoRequest;
import br.com.fiap.api_rest.dto.ProdutoResponse;
import br.com.fiap.api_rest.mapper.ProdutoMapper;
import br.com.fiap.api_rest.model.Produto;
import br.com.fiap.api_rest.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository,  ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    // CRUD
    public Produto create(ProdutoRequest produtoRequest) {
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoRequest, produto);
        return produtoRepository.save(produto);
    }

    public ProdutoResponse read(UUID id) {
        Optional<Produto> produto =  produtoRepository.findById(id);
        if(produto.isEmpty()){
            return null;
        }
        return produtoMapper.produtoToResponse(produto.get());
    }

    public List<ProdutoResponse> read() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos
                .stream()
                .map(produtoMapper::produtoToResponse)
                .collect(Collectors.toList());
    }

    // Page, Pageable
    public Page<ProdutoLista> read(Pageable pageable) {
        return produtoRepository
                .findAll(pageable)
                .map(produtoMapper::produtoToProdutoLista);
    }

    /*
    // Exemplo usando for em vez de stream
    public List<ProdutoRequest> read(){
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponse> produtoResponse = new ArrayList<>();
        for (Produto produto : produtos){
            produtoResponse.add(produtoMapper.produtoToResponse(produto));
        }
        return produtoResponse;
     }
     */
    public Produto update(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void delete(UUID id) {
        produtoRepository.deleteById(id);
    }
}

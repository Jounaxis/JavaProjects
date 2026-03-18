package br.com.fiap.api_rest.dto;

import br.com.fiap.api_rest.model.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponse (UUID id, String nome, BigDecimal preco) {
}

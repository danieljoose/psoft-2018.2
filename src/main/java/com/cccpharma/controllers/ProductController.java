package com.cccpharma.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cccpharma.models.Produto;
import com.cccpharma.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/produtos/orderbyprice", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<Produto> getProductsperPrice() {
		return this.productService.orderByPrice();
	}
	
	@RequestMapping(value = "/produtos/orderbyname", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<Produto> getProductsperName() {
		return this.productService.orderByNome();
	}
	
	@RequestMapping(value = "/produtos/crud", method = RequestMethod.DELETE)
	public String deleteProduct(String codigo) {
		Produto p = this.productService.findByCodigo(codigo);
		this.productService.deleteProduct(p);
		return "Produto excluído";
	}
	
	@RequestMapping(value = "/produtos/crud", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public Produto addProduct(@RequestBody Produto produto) {
		return this.productService.cadastrar(produto);
	}
	
	@RequestMapping(value = "/produtos/crud/{codigo}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public Double serPrice(@PathVariable String codigo, @RequestBody Double preco) {
		return this.productService.mudarPreco(codigo, preco);
	}

	@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<Produto> getProducts() {
		return this.productService.getAll();
	}
}
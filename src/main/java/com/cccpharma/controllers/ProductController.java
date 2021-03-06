package com.cccpharma.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cccpharma.models.Produto;
import com.cccpharma.services.ProductService;

@CrossOrigin(value = "*")
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
	
	@RequestMapping(value = "/produtos/orderbycategory", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<Produto> getProductsByCategory() {
		return this.productService.orderByCategory();
	}
	
	@RequestMapping(value = "/produtos/crud/{codigo}", method = RequestMethod.DELETE)
	public Produto deleteProduct(@PathVariable String codigo) {
		return this.productService.deleteProduct(codigo);
	}
	
	@RequestMapping(value = "/produtos/crud", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public Produto addProduct(@RequestBody Produto produto) throws Exception {
		return this.productService.cadastrar(produto);
	}
	
	@RequestMapping(value = "/produtos/crud/{codigo}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public Double serPrice(@PathVariable String codigo, @RequestBody Double preco) throws Exception {
		return this.productService.mudarPreco(codigo, preco);
	}

	@RequestMapping(value = "/produtos/desconto/{categoria}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public void atribuirDesconto(@PathVariable String categoria, @RequestBody Integer desconto) {
		this.productService.atribuirDesconto(categoria, desconto);
	}
		
	@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<Produto> getProducts() {
		return this.productService.getAll();
	}
}

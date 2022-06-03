package com.example.demo.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.model.Product;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Mock
	@Autowired
    private ProductRepository productRepository;
    private Product product;
    @BeforeEach
    public void setUp() {
        product = new Product("Bat",2500);
    }
    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
        product = null;
    }
    
    @Test
    public void givenProductToAddShouldReturnAddedProduct(){
         productRepository.save(product);
         Product fetchedProduct = productRepository.findById(product.getId()).get();
         assertEquals(1, fetchedProduct.getId());
    }
    
    @Test
    public void GivenGetAllProductShouldReturnListOfAllProducts(){
        Product product1 = new Product("ball",400);
        Product product2 = new Product("bat",500);
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> productList = (List<Product>) productRepository.findAll();
        assertEquals("bat", productList.get(1).getName());

    }

    @Test
    public void givenIdThenShouldReturnProductOfThatId() {
        Product product1 = new Product("bat",3000);
        Product product2 = productRepository.save(product1);

        Optional<Product> optional =  productRepository.findById(product2.getId());
        assertEquals(product2.getId(), optional.get().getId());
        assertEquals(product2.getName(), optional.get().getName());
    }

    @Test
    public void givenIdTODeleteThenShouldDeleteTheProduct() {
        Product product = new Product("pen",160);
        productRepository.save(product);
        productRepository.deleteById(product.getId());
        Optional<Product> optional = productRepository.findById(product.getId());
        assertEquals(Optional.empty(), optional);
    }
    



}


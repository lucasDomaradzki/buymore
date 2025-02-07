package com.ecommerce.buymore.controller.product;

import com.ecommerce.buymore.common.exception.BuyMoreException;
import com.ecommerce.buymore.common.exception.InternalServerErrorException;
import com.ecommerce.buymore.model.product.dto.ProductCreateDTO;
import com.ecommerce.buymore.model.product.dto.ProductDeleteDTO;
import com.ecommerce.buymore.model.product.dto.ProductDetailDTO;
import com.ecommerce.buymore.model.product.dto.ProductUpdateDTO;
import com.ecommerce.buymore.model.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "v1/products")
@Tag(name = "Product", description = "Product API")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(description = "Creates new product with provided payload information", summary = "Creates a new product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Product created successfully"),
        @ApiResponse(responseCode = "208", description = "Product already exists"),
        @ApiResponse(responseCode = "400", description = "Unexpected data sent in request - Bad Request"),
        @ApiResponse(responseCode = "500", description = "Unexpected error - Internal server error")
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCreateDTO> createProduct(@RequestBody ProductCreateDTO product) throws BuyMoreException {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @Operation(description = "Count all product records", summary = "Count all product records")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Count of product records fetched successfully"),
        @ApiResponse(responseCode = "404", description = "Count of product records not found - Not Found"),
        @ApiResponse(responseCode = "500", description = "Unexpected error - Internal server error")
    })
    @GetMapping(value = "/count")
    public ResponseEntity<Long> countAllRecords() throws BuyMoreException {
        Long productCount = productService
                .countAllProducts().orElseThrow(() -> new InternalServerErrorException("Could not count products"));
        return ResponseEntity.ok(productCount);
    }

    @Operation(description = "Fetch all existing products", summary = "Fetch all products")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Products fetched successfully"),
        @ApiResponse(responseCode = "404", description = "Products not found - Not Found"),
        @ApiResponse(responseCode = "500", description = "Unexpected error - Internal server error")
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDetailDTO>> findProducts() throws BuyMoreException {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @Operation(description = "Fetch a specific product by a given product id", summary = "Fetch a product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product fetched successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found - Not Found"),
        @ApiResponse(responseCode = "500", description = "Unexpected error - Internal server error")
    })
    @GetMapping(value = "/{productId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDetailDTO> findProductById(@PathVariable(name = "productId") Long productId) throws BuyMoreException {
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @Operation(description = "Fetch all matching products by a given name", summary = "Fetch products by given name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product(s) fetched successfully"),
        @ApiResponse(responseCode = "400", description = "Product name not informed - Missing parameter"),
        @ApiResponse(responseCode = "404", description = "Product(s) not found - Not Found"),
        @ApiResponse(responseCode = "500", description = "Unexpected error - Internal server error")
    })
    @GetMapping(value = "/name", params = "productName", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDetailDTO>> findProductsByName(@RequestParam String productName) throws BuyMoreException {
        return ResponseEntity.ok(productService.findProductsByName(productName));
    }

    @Operation(description = "Update product partially", summary = "Product partial update")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Product partially updated successfully"),
        @ApiResponse(responseCode = "400", description = "Unexpected data sent in request - Bad Request"),
        @ApiResponse(responseCode = "404", description = "Product not found - Not Found"),
        @ApiResponse(responseCode = "500", description = "Unexpected error - Internal server error")
    })
    @PatchMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductUpdateDTO> patchProduct(@RequestBody ProductUpdateDTO product) throws BuyMoreException {
        product = productService.patchProduct(product);
        return ResponseEntity.ok(product);
    }

    @Operation(description = "Update entire product by given payload", summary = "Update product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Product partially updated successfully"),
        @ApiResponse(responseCode = "400", description = "Unexpected data sent in request - Bad Request"),
        @ApiResponse(responseCode = "404", description = "Product not found - Not Found"),
        @ApiResponse(responseCode = "500", description = "Unexpected error - Internal server error")
    })
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductUpdateDTO> updateProduct(@RequestBody ProductUpdateDTO product) throws BuyMoreException {
        product = productService.updateProduct(product);
        return ResponseEntity.ok(product);
    }

    @Operation(description = "Delete given product, by product id and provided delete comment", summary = "Delete a product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Unexpected data sent in request - Bad Request"),
        @ApiResponse(responseCode = "404", description = "Product not found - Not Found"),
        @ApiResponse(responseCode = "500", description = "Unexpected error - Internal server error")
    })
    @DeleteMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProduct(@RequestBody ProductDeleteDTO deleteProduct) throws BuyMoreException {
        productService.deleteProduct(deleteProduct);
        return ResponseEntity.noContent().build();
    }

}

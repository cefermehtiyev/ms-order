package az.ingress.client;

import az.ingress.model.dto.ProductDto;
import az.ingress.model.dto.ProductIdsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ms-product",path = "internal/v1/products",url = "${client.urls.ms-product}")
public interface ProductClient {
    @PostMapping
    List<ProductDto> getProduct(@RequestBody ProductIdsDto productIdsDto);

}

package com.sj.Model;

import com.sj.entity.Product;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {
	private long productId;
    private String productName;
    private long price;
    private long quantity;
    private OrderResponse orderResponse;
	
    public ProductResponse(long productId, String productName, long price, long quantity, OrderResponse orderResponse) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.orderResponse = orderResponse;
	}
  

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price + ", quantity="
				+ quantity  + "]";
	}

	// Static factory method for conversion
    public static ProductResponse fromEntity(Product product) {
        ProductResponse response = new ProductResponse();
        response.setProductId(product.getProductId());
        response.setProductName(product.getProductName());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        return response;
    }

	
}

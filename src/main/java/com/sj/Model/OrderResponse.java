package com.sj.Model;

import java.time.Instant;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {
	private long Id;
	private long productId;
    private long quantity;
    Instant orderDate;
    String orderStatus;
    private long amount;
    
    
	public OrderResponse(long id, long productId, long quantity, Instant orderDate, String orderStatus, long amount) {
		super();
		Id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.amount = amount;
	}	
	
}

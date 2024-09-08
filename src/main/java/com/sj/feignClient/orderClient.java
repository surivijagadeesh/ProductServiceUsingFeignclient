package com.sj.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sj.Model.OrderResponse;

//http://localhost:8081/order-service/api/getOrdersbyID/4
@FeignClient(name="order-service1" ,url="http://localhost:8081/order-service/api/")
public interface orderClient {

	@GetMapping("/getOrdersbyID/{id}")
	OrderResponse getOrderResposnseByID(@PathVariable ("id") Long id);
}

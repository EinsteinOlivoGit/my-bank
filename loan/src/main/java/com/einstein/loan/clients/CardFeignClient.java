package com.einstein.loan.clients;

import com.einstein.loan.dtos.ConsultCardOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("card")
public interface CardFeignClient {

    @GetMapping(value = "/api/v1/cards", consumes = "application/json")
    ResponseEntity<ConsultCardOutput> consultCard(@RequestParam String mobileNumber);
}

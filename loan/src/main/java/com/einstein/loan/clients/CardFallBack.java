package com.einstein.loan.clients;

import com.einstein.loan.dtos.ConsultCardOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardFallBack implements CardFeignClient {
    @Override
    public ResponseEntity<ConsultCardOutput> consultCard(String correlationId, String mobileNumber) {
        return null;
    }
}

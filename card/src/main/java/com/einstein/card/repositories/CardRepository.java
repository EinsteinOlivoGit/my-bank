package com.einstein.card.repositories;

import com.einstein.card.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByMobileNumber(String mobileNumber);
    Optional<Card> findByCardNumber(String cardNumber);
}

package project.book.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.book.model.Cards;
import project.book.repository.CardsRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(@RequestParam("id") long id){

        List<Cards> cards = cardsRepository.findByCustomerId(id);
        if(cards != null) {
            return cards;
        }else {
            return null;
        }
    }
}

package project.book.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.book.model.Contact;
import project.book.repository.ContactRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactRepository contactRepository;
    @PostMapping("/contact")
    @PreFilter("filterObject.contactName != 'Test'")
    public Contact getContact(@RequestBody List<Contact> contacts) throws ParseException {
        Contact contact = contacts.get(0);
        if (null != contact){
            contact.setContactId(getServiceReqNumber());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date();
            String current = simpleDateFormat.format(date);
            contact.setCreateDt(current);
            return contactRepository.save(contact);
        }else {
            return null;
        }
    }

    public String getServiceReqNumber(){
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }



}

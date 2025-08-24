package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageController {
    @GetMapping("/message/basic")
    public String getMessageBasic() {
        return "message/message-basic";
    }

    @GetMapping("/message/customer")
    public String getMessageCustomer(Model model) {
        model.addAttribute("name", "한빛미디어");
        model.addAttribute("phone", "02-325-0384");
        return "message/message-customer";
    }

    @GetMapping("/message/key")
    public String getMessageKey(Model model) {
        model.addAttribute("contactFrom", "customer.contact.short");
        model.addAttribute("name", "한빛미디어");
        model.addAttribute("phone", "02-325-0384");
        return "message/message-key";
    }
}

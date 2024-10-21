package com.cursomateus.pizzariadankicode.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteRetorno {

    @GetMapping
    public String testeRetorno() {
        return "Olá, este é o retorno do teste";
    }
}

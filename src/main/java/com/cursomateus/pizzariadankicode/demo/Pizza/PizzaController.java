package com.cursomateus.pizzariadankicode.demo.Pizza;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class PizzaController {

    private final PizzaService pizzaService;

    @PostMapping
    public ResponseEntity<PizzaDto> createPizza(@RequestBody @Valid PizzaDto pizzaDto, UriComponentsBuilder uriBuilder) {
        PizzaDto pizza = pizzaService.criarPizza(pizzaDto);
        URI endereco = uriBuilder.path("/pizzas/{id}").buildAndExpand(pizzaDto.getId()).toUri();

        return ResponseEntity.created(endereco).body(pizzaDto);
    }

    @GetMapping
    public ResponseEntity<Page<PizzaDto>> getPizzas(@PageableDefault(size = 10) Pageable paginacao) {
        Page<PizzaDto> pizzas = pizzaService.getPizzas(paginacao);
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDto> getById(@PathVariable @NotNull Long id) {
        PizzaDto pizzaDto = pizzaService.getById(id);
        return ResponseEntity.ok(pizzaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaDto> update(@RequestBody PizzaDto dto, @PathVariable @NotNull Long id) {
        PizzaDto pizzaAtualizada = pizzaService.atualizarPizza(dto, id);
        return ResponseEntity.ok(pizzaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable @NotNull Long id) {
        pizzaService.excluir(id);
        return ResponseEntity.noContent().build();
    }


}



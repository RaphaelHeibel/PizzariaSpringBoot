package com.cursomateus.pizzariadankicode.demo.Pizza;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final ModelMapper modelMapper;

    public PizzaDto criarPizza(PizzaDto pizzaDto) {
        Pizza pizza = modelMapper.map(pizzaDto, Pizza.class);
        pizzaRepository.save(pizza);

        return modelMapper.map(pizza, PizzaDto.class);
    }


    public Page<PizzaDto> getPizzas(Pageable paginacao) {
        return pizzaRepository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p,PizzaDto.class));
    }

    public PizzaDto getById(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pizza não encontrada"));
        return modelMapper.map(pizza, PizzaDto.class);
    }

    public PizzaDto atualizarPizza(PizzaDto dto, Long id) {
        Pizza pizzaById = pizzaRepository.findById(id).orElse(null);
        if (pizzaById != null) {
            Pizza pizza = modelMapper.map(dto, Pizza.class);
            pizza.setId(id);
            pizza = pizzaRepository.save(pizza);
            return modelMapper.map(pizza, PizzaDto.class);
        }

        return null;
    }

    public void excluir(Long id) {
        pizzaRepository.deleteById(id);
    }
}

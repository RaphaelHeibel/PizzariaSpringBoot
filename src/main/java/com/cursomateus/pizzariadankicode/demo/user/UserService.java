package com.cursomateus.pizzariadankicode.demo.user;

import com.cursomateus.pizzariadankicode.demo.Config.CriptografiaSenha;
import com.cursomateus.pizzariadankicode.demo.Pizza.PizzaDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository _repository;
    private final ModelMapper _modelMapper;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return _repository.findByLogin(login);
    }

    public UserDto CreateUser(UserDto dto) {
        Usuario usuario = _modelMapper.map(dto, Usuario.class);
        String senhaCriptografada = CriptografiaSenha.criptografia(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);
        _repository.save(usuario);

        return _modelMapper.map(usuario, UserDto.class);
    }

    public Page<UserDto> getUsers(Pageable paginacao) {
        return _repository.findAll(paginacao).map(u -> _modelMapper.map(u, UserDto.class));
    }

    public UserDto getUserById(Long id) {
        Usuario user = _repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        return _modelMapper.map(user, UserDto.class);
    }

    public UserDto updateUser(UserDto dto, Long id) {
        Usuario userById = _repository.findById(id).orElse(null);
        if (userById != null) {
            Usuario user = _modelMapper.map(dto, Usuario.class);
            String senhaCriptografada = CriptografiaSenha.criptografia(user.getPassword());
            user.setPassword(senhaCriptografada);
            user.setId(id);
            user = _repository.save(user);
            return _modelMapper.map(user, UserDto.class);
        }
        return _modelMapper.map(userById, UserDto.class);
    }

    public void deleteUser(Long id) {
        Usuario userById = _repository.findById(id).orElse(null);

        if (userById != null)
            _repository.deleteById(id);

    }
}

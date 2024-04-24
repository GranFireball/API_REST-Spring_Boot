package com.leonardo.todosimple.services;


import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leonardo.todosimple.repositories.UserRepository;
import com.leonardo.todosimple.services.exceptions.DataBindingViolationException;
import com.leonardo.todosimple.services.exceptions.ObjectNotFoundException;
import com.leonardo.todosimple.models.User;
import com.leonardo.todosimple.models.enums.ProfileEnum;

@Service
public class UserService {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private UserRepository userRepository;

  public User findById(Long id){
    Optional<User> user = this.userRepository.findById(id);
    return user.orElseThrow(() -> new ObjectNotFoundException(
      "Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()
    ));
  }

  @Transactional
  public User create(User obj){
    obj.setId(null);
    obj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
    obj.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
    obj = this.userRepository.save(obj);
    return obj;
  }

  @Transactional
  public User update(User obj){
    User newObj = findById(obj.getId());
    newObj.setPassword(obj.getPassword());
    newObj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
    return this.userRepository.save(newObj);
  }

  public void delete(Long id){
    findById(id);
    try{
      this.userRepository.deleteById(id);
    }
    catch(Exception e){
      throw new DataBindingViolationException("Não é possível excluir pois há entidades relacionadas!");
    }
  }
}

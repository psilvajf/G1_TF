package org.g1tf.service;
import org.g1tf.dto.*; import org.g1tf.model.Jogador; import org.g1tf.repository.JogadorRepository;
import org.springframework.stereotype.Service;
@Service
public class AuthService{
 private final JogadorRepository repo;
 public AuthService(JogadorRepository repo){this.repo=repo;}
 public Jogador register(RegisterDTO dto){
   if(repo.existsByEmail(dto.getEmail())) throw new RuntimeException("Email ja cadastrado");
   Jogador j=new Jogador();
   j.setNome(dto.getNome()); j.setEmail(dto.getEmail()); j.setSenha(dto.getSenha()); j.setApelido(dto.getApelido());
   return repo.save(j);
 }
 public boolean login(LoginDTO dto){
   return repo.findByEmail(dto.getEmail()).map(j->j.getSenha().equals(dto.getSenha())).orElse(false);
 }
}
package br.com.desafioP.DpCrud.repository;

import br.com.desafioP.DpCrud.entity.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagensRepository extends JpaRepository<Personagem,Integer> {
}

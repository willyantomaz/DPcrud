package br.com.desafioP.DpCrud.repository;

import br.com.desafioP.DpCrud.entity.ItensMagicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensMagicosRepository extends JpaRepository<ItensMagicos,Integer> {
}

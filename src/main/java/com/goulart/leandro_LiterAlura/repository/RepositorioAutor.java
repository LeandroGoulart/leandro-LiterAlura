package com.goulart.leandro_LiterAlura.repository;
import com.goulart.leandro_LiterAlura.model.Autor;

public interface RepositorioAutor extends JpaRepository<Autor, Long>{
    Boolean existsByNome(String nome);

    Autor findByNome(String nome);

    @Query("SELECT a FROM Author a WHERE a.dataDeFalecimento >= :anoSelecionado AND :anoSelecionado >= a.dataDeNascimento")
    List<Autor> buscarPorAnoDeFalecimento(int anoSelecionado);
}
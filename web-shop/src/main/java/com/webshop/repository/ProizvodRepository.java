package com.webshop.repository;

import com.webshop.Enumeracije.TipProdaje;
import com.webshop.dto.ProizvodDto;
import com.webshop.model.Kategorija;
import com.webshop.model.Proizvod;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProizvodRepository extends JpaRepository<Proizvod,Long> {
    //Page<Proizvod> findAll(Pageable pageable);
    //List<Proizvod> findAllByNaziv(String naziv,Pageable pageable);
   // List<Proizvod> findAllByCenaAndNaziv(double cena,String naziv,Pageable pageable);
    List<Proizvod> findProizvodByProdavacId(long id);
    Optional<List<Proizvod>> findAllByNazivContainsIgnoreCaseOrOpisContainsIgnoreCase(String naziv, String opis);
    @Query("SELECT p FROM Proizvod p WHERE (:minPrice IS NULL OR p.cena >= :minPrice) AND (:maxPrice IS NULL OR p.cena <= :maxPrice) AND (:saleType IS NULL OR p.tipProdaje = :saleType) AND (:category IS NULL OR p.kategorija.naziv = :category)")
    List<Proizvod> findProductsByFilters(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, @Param("saleType") TipProdaje.tipProdaje tipProdaje, @Param("category") String category);
}


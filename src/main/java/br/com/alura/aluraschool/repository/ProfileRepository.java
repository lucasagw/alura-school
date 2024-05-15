package br.com.alura.aluraschool.repository;

import br.com.alura.aluraschool.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ProfileRepository extends JpaRepository<Profile, Long> {


    Profile findByName(String name);


    @Query("SELECT p FROM Profile p WHERE p.name IN :names")
    Set<Profile> findByNames(Set<String> names);
}

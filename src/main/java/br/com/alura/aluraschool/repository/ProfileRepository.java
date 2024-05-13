package br.com.alura.aluraschool.repository;

import br.com.alura.aluraschool.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {


    Profile findByName(String name);
}

package br.com.alura.aluraschool.repository;

import br.com.alura.aluraschool.model.entity.UserKey;
import br.com.alura.aluraschool.model.entity.UserSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserSchoolRepository extends JpaRepository<UserSchool, UserKey> {


    Optional<UserSchool> findByUserKeyUsername(String username);


    @Query(value = "SELECT u.* FROM USER_SCHOOL u " +
            "WHERE u.USERNAME = :username AND u.EMAIL = :email", nativeQuery = true)
    Optional<UserSchool> findByUsernameAndEmail(String username, String email);


    @Query(value = "SELECT * FROM PUBLIC.USER_SCHOOL " +
            "WHERE USERNAME = :username OR EMAIL = :email", nativeQuery = true)
    Optional<UserSchool> customFindByUsernameOrEmail(String username, String email);


    @Query("SELECT CASE WHEN (COUNT(u) > 0) THEN true ELSE false END FROM UserSchool u WHERE u.userKey.username = :username " +
            "OR u.userKey.email = :email")
    boolean existsByUsernameOrEmail(String username, String email);

}

package com.codecmd.institute.repository;

import com.codecmd.institute.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface StudentRepository extends JpaRepository<Student, String>, JpaSpecificationExecutor<Student> {

    @Query("select c from Student c where upper(c.login) = upper(?1)")
    Optional<Student> findLoggedInCustomer(String login);

    Optional<Student> findOneByEmailIgnoreCase(String email);

    Optional<Student> findOneByLoginIgnoreCase(String login);
}

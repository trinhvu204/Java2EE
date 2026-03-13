package com.example.Lap05.repository;

import com.example.Lap05.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT a FROM Account    a WHERE a.login_name = :login_name")
    Optional<Account> findByLoginName(String login_name);
}

package com.onefans.identity_service.modules.accounts.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onefans.identity_service.modules.accounts.entity.Account;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    boolean existsByEmail(String s);

    Optional<Account> findByEmailAndProvider(String email, String provider);
}

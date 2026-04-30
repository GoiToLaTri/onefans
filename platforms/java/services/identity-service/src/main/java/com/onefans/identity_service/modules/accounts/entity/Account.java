package com.onefans.identity_service.modules.accounts.entity;

import java.time.LocalDate;

import com.onefans.common.enums.EAccountStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String password;

    @Column(updatable = false)
    String email;

    @Column(nullable = false)
    String provider;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    EAccountStatus accountStatus;

    @Column(nullable = false)
    boolean isBlock;

    @Column(nullable = false, updatable = false)
    LocalDate createAt;

    @PrePersist
    protected void onCreate(){
        if(createAt == null)
            createAt = LocalDate.now();

        if(provider == null)
            provider = "onefans";

        if(accountStatus == null)
            accountStatus = EAccountStatus.PENDING;

        if(isBlock)
            isBlock = false;
    }
}

package com.workintech.s18d4.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

public record CustomerResponse(
        String firstName,
        String lastName,
        String email,
        Double salary,
        Address address,
        List<Account> accounts

) {
}


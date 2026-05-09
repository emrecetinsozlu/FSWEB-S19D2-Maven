package com.workintech.s18d4.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "customer", schema = "banking")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "email")
    private String email;
    private Double salary;
    // cascade genelde owner tarafına verilir
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    // bu bilgi ne olarak tutulsun address_id ismiyle id olarak tutulsun
    @JoinColumn(name = "address_id")
    private Address address;

    // customer silinirse accountları kalmasın
    @OneToMany(mappedBy = "customer" ,cascade = CascadeType.ALL)
    //burda joincolumn yok çünkü burası foreignkey tutmayacak
    //Ama business logic de burası parent yani customer olmazsa accountları da olmaz.
    @JsonManagedReference
    //jsonmanagegrefrence db dönüşlerinde customer da accountların görünmesini salar
    private List<Account> accounts = new ArrayList<>();

}

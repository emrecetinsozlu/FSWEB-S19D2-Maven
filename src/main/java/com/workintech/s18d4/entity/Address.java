package com.workintech.s18d4.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address", schema = "banking")

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private Integer no;
    private String city;
    private String country;
    private String description;
    //owner customer dependent Address
    //eğer customer silinirse addressin bir anlamı yok o yüzden cascade tarafını customer a verdik.
    //mappedBy; bu ilişkiyi ben yönetmiyorum customer entitysindeki address feildi yönetiyor demek.
    // db de Address içinde foreign key olmayacağı için joincolumn da yazmamış oluyoruz buraya.
    @OneToOne(mappedBy = "address")
    private Customer customer;
}

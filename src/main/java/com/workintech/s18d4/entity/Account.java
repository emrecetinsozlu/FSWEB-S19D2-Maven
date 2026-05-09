package com.workintech.s18d4.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account", schema = "banking")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="account_name")
    private String accountName;
    @Column(name = "money_amount")
    private Double moneyAmount;
    // relational db de mantık şu çok olan taraf foreign key tutar!
    //foreign key tutan taraf da owner side olur
    // bir customer ın birden fazla account u olabilir o yüzden account foreign key tutar ve o yüzden owner o dur.
    // Parent ve Owner farklı kavramlar
    // Parent Childın bağlı olduğu obje yani Customer Parenttır ama Owner foreign key tutan taraf olduğu için Accounttur
    // jsonbackrefrence db dönüşünde accountlarda customer ın gözük memesini salar
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
/*
account tablosu (id, account_name, money_amount, customer_id) kolonlarından oluşmalı.
id primary_key olarak işaretlenmeli.
Bir Customer'in birden çok Account'u olabilir.

*/

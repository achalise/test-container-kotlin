package com.example.testcontainerintegrationkotlin.repository

import org.springframework.data.repository.CrudRepository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

interface TransactionRepository: CrudRepository<Transaction, String>

@Entity
data class Transaction(
    @Id
    var id: String,
    @Column
    var name: String,
    @Column
    var description: String)
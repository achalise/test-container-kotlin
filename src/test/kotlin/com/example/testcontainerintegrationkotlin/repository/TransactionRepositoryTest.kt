package com.example.testcontainerintegrationkotlin.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class TransactionRepositoryTest {
    companion object {
        @Container
        private val container = MySQLContainer<Nothing>("mysql:8.0.23")

        @DynamicPropertySource
        @JvmStatic
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", container::getJdbcUrl)
            registry.add("spring.datasource.username", container::getUsername)
            registry.add("spring.datasource.password", container::getPassword)
        }
    }


    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Test
    fun `Transaction can be saved and retrieved`() {
        val transaction = Transaction("1", "name", "description")
        val saved = transactionRepository.save(transaction)
    }
}
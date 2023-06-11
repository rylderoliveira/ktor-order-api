package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class OrderRoutesTest {
    @Test
    fun `get order with valid id`() = testApplication {
        application {
            module()
        }
        client.get("/order/2020-04-06-01").apply {
            assertEquals(
                """{"number":"2020-04-06-01","contents":[{"item":"Ham Sandwich","amount":2,"price":5.5},{"item":"Water","amount":1,"price":1.5},{"item":"Beer","amount":3,"price":2.3},{"item":"Cheesecake","amount":1,"price":3.75}]}""",
                bodyAsText()
            )
            assertEquals(HttpStatusCode.OK, status)
        }
    }

    @Test
    fun `get order with null id`() = testApplication {
        application {
            module()
        }
        client.get("/order/").apply {
            assertEquals("Bad Request", bodyAsText())
            assertEquals(HttpStatusCode.BadRequest, status)
        }
    }
}
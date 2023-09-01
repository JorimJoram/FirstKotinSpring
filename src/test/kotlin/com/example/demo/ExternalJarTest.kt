package com.example.demo

import gameone.LoadAndCsv
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExternalJarTest {
    @Test
    fun externalTest(){
        val external = LoadAndCsv("http://www.gameone.kr/club/info/ranking/hitter?club_idx=14106&season=2023")
        external.loadAndCsv()
    }
}
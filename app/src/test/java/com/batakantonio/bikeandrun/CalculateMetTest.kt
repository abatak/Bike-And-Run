package com.batakantonio.bikeandrun

import com.batakantonio.bikeandrun.data.util.CalculateMET
import org.junit.Test
import kotlin.test.assertEquals


class CalculateMetTest {

    @Test
    fun calculateMET_slowWalking_returns2() {
        val met = CalculateMET.estimateMET(3.0)
        assertEquals(2.0, met)
    }

    @Test
    fun calculateMET_normalWalking_returns3point5() {
        val met = CalculateMET.estimateMET(5.5)
        assertEquals(3.5, met)
    }

    @Test
    fun calculateMET_briskWalking_returns5() {
        val met = CalculateMET.estimateMET(7.4)
        assertEquals(5.0, met)
    }

    @Test
    fun calculateMET_lightJogging_returns7() {
        val met = CalculateMET.estimateMET(10.0)
        assertEquals(7.0, met)
    }

    @Test
    fun calculateMET_running_returns9() {
        val met = CalculateMET.estimateMET(15.5)
        assertEquals(9.0, met)
    }
    @Test
    fun calculateMET_fastRunning_returns12() {
        val met = CalculateMET.estimateMET(18.0)
        assertEquals(12.0, met)
    }
}
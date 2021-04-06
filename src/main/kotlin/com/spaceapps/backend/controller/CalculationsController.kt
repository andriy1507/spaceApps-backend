package com.spaceapps.backend.controller

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.abs
import kotlin.random.Random

@RestController
@Api(tags = ["Calculations"], description = "Calculations endpoints")
@RequestMapping("calc")
class CalculationsController {

    data class CalcRequest(
        val actualData: List<Int>,
        val roundsCount: Int
    )

    @PostMapping
    fun calculate(@RequestBody request: CalcRequest): List<Double> {
        val actualResult = request.actualData.last()
        var lastCalcRequest = 0.0
        val myWeights = provideRandomWeights(request.actualData.size - 1)
        for (r in 0..request.roundsCount) {
            var newResult = 0.0
            val newWeights = provideRandomWeights(request.actualData.size - 1)
            newWeights.forEachIndexed { index, d ->
                newResult += request.actualData[index] * d
            }
            if (abs(actualResult-lastCalcRequest) > abs(actualResult-newResult)) {
                lastCalcRequest = newResult
                myWeights.clear()
                myWeights.addAll(newWeights)
            }
        }
        return myWeights
    }

    private fun provideRandomWeights(size: Int): MutableList<Double> {
        val weights = mutableListOf<Double>()
        for (i in 0 until size) {
            weights.add(Random.nextDouble(0.0, 1.0))
        }
        weights.sort()
        return weights
    }
}
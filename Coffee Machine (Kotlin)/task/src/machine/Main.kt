package machine

import kotlin.math.max

const val WATER_PER_CUP = 200
const val MILK_PER_CUP = 50
const val BEANS_PER_CUP = 15
fun main() {
//    println("Starting to make a coffee")
//    println("Grinding coffee beans")
//    println("Boiling water")
//    println("Mixing boiled water with crushed coffee beans")
//    println("Pouring coffee into the cup")
//    println("Pouring some milk into the cup")
//    println("Coffee is ready!")
    println("Write how many ml of water the coffee machine has:")
    val waterAvailable = readln().toInt()
    println("Write how many ml of milk the coffee machine has:")
    val milkAvailable = readln().toInt()
    println("Write how many grams of coffee beans the coffee machine has:")
    val beansAvailable = readln().toInt()
    println("Write how many cups of coffee you will need:")
    val cupsNb = readln().toInt()

//    val waterNeeded = cupsNb* WATER_PER_CUP
//    val milkNeeded = cupsNb* MILK_PER_CUP
//    val beansNeeded = cupsNb* BEANS_PER_CUP

    val maxCups = calculateMaxCups(waterAvailable, milkAvailable, beansAvailable)

    if (cupsNb > maxCups) {
        println("No, I can make only $maxCups cups of coffee")
    } else if (cupsNb == maxCups) {
        println("Yes, I can make that amount of coffee")
    } else println("Yes, I can make that amount of coffee (and even ${maxCups - cupsNb} more than that)")


//    println("For $cupsNb cups of coffee you will need:")

//    println("$water ml of water")
//    println("$milk ml of milk")
//    println("$beans g of coffee beans")

}

fun calculateMaxCups(waterAvailable: Int, milkAvailable: Int, beansAvailable: Int): Int {

    return minOf(waterAvailable / WATER_PER_CUP, milkAvailable / MILK_PER_CUP, beansAvailable / BEANS_PER_CUP)
}

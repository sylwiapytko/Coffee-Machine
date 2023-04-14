package machine

const val WATER_PER_CUP = 200
const val MILK_PER_CUP = 50
const val BEANS_PER_CUP = 15

class CoffeeSpecs(val water: Int, val milk: Int, val beans: Int, val cost: Int) {}
class CoffeeMachineSpecs(var water: Int = 400, var milk: Int = 540, var beans: Int= 120, var cups: Int = 9 , var cash: Int = 550)

fun main() {
    val espresso = CoffeeSpecs(250, 0, 16, 4)
    val latte = CoffeeSpecs(350, 75, 20, 7)
    val cappuccino = CoffeeSpecs(200, 100, 12, 6)

    val coffeeList = listOf<CoffeeSpecs>(espresso, latte, cappuccino)
    val coffeeMachineSpecs = CoffeeMachineSpecs()

    printCoffeeMachineSpecs(coffeeMachineSpecs)

    println("Write action (buy, fill, take):")
    val action = readln()
    when (action) {
        "buy" -> buyCoffee(coffeeMachineSpecs, coffeeList)
        "fill" -> fillCoffeeMachine(coffeeMachineSpecs)
        "take" -> takeCoffeeMoney(coffeeMachineSpecs)
    }
    printCoffeeMachineSpecs(coffeeMachineSpecs)

}

private fun printCoffeeMachineSpecs(coffeeMachineSpecs: CoffeeMachineSpecs) {
    println("The coffee machine has:")
    println("${coffeeMachineSpecs.water} ml of water")
    println("${coffeeMachineSpecs.milk} ml of milk")
    println("${coffeeMachineSpecs.beans} g of coffee beans")
    println("${coffeeMachineSpecs.cups} disposable cups")
    println("${coffeeMachineSpecs.cash} money")
}

private fun printCoffeeMakingActions() {
    println("Starting to make a coffee")
    println("Grinding coffee beans")
    println("Boiling water")
    println("Mixing boiled water with crushed coffee beans")
    println("Pouring coffee into the cup")
    println("Pouring some milk into the cup")
    println("Coffee is ready!")
}

private fun checkCoffeeAvailability(waterAvailable: Int, milkAvailable: Int, beansAvailable: Int) {
    val cupsRequested = readln().toInt()
    val maxCups = calculateMaxCups(waterAvailable, milkAvailable, beansAvailable)

    if (cupsRequested > maxCups) {
        println("No, I can make only $maxCups cups of coffee")
    } else if (cupsRequested == maxCups) {
        println("Yes, I can make that amount of coffee")
    } else println("Yes, I can make that amount of coffee (and even ${maxCups - cupsRequested} more than that)")
}

fun takeCoffeeMoney(coffeeMachineSpecs: CoffeeMachineSpecs) {
    println("I gave you ${coffeeMachineSpecs.cash}")
    coffeeMachineSpecs.cash=0
}

fun buyCoffee(coffeeMachineSpecs: CoffeeMachineSpecs, coffeeList: List<CoffeeSpecs>) {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")
    when(readln()){
        "1"-> makeCoffee(coffeeMachineSpecs, coffeeList[0])
        "2"-> makeCoffee(coffeeMachineSpecs, coffeeList[1])
        "3"-> makeCoffee(coffeeMachineSpecs, coffeeList[2])
    }
}

fun makeCoffee(coffeeMachineSpecs: CoffeeMachineSpecs, coffeeSpecs: CoffeeSpecs) {
    coffeeMachineSpecs.water -= coffeeSpecs.water
    coffeeMachineSpecs.milk -= coffeeSpecs.milk
    coffeeMachineSpecs.beans -= coffeeSpecs.beans
    coffeeMachineSpecs.cups --
    coffeeMachineSpecs.cash += coffeeSpecs.cost
}

fun fillCoffeeMachine(coffeeMachineSpecs: CoffeeMachineSpecs) {
    println("Write how many ml of water you want to add:")
     coffeeMachineSpecs.water += readln().toInt()
    println("Write how many ml of milk you want to add:")
    coffeeMachineSpecs.milk += readln().toInt()
    println("Write how many grams of coffee beans you want to add:")
    coffeeMachineSpecs.beans += readln().toInt()
    println("Write how many disposable cups you want to add:")
    coffeeMachineSpecs.cups += readln().toInt()
}

fun calculateMaxCups(waterAvailable: Int, milkAvailable: Int, beansAvailable: Int): Int {

    return minOf(waterAvailable / WATER_PER_CUP, milkAvailable / MILK_PER_CUP, beansAvailable / BEANS_PER_CUP)
}

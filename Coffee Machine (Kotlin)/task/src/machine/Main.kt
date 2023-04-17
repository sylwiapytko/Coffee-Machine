package machine

class CoffeeSpecs(val water: Int, val milk: Int, val beans: Int, val cost: Int) {}
class CoffeeMachineSpecs(var water: Int = 400, var milk: Int = 540, var beans: Int = 120, var cups: Int = 9, var cash: Int = 550)

fun main() {
    val espresso = CoffeeSpecs(250, 0, 16, 4)
    val latte = CoffeeSpecs(350, 75, 20, 7)
    val cappuccino = CoffeeSpecs(200, 100, 12, 6)

    val coffeeList = listOf<CoffeeSpecs>(espresso, latte, cappuccino)
    val coffeeMachineSpecs = CoffeeMachineSpecs()

    do {
        println("Write action (buy, fill, take, remaining, exit):")
        val action = readln()
        when (action) {
            "buy" -> buyCoffee(coffeeMachineSpecs, coffeeList)
            "fill" -> fillCoffeeMachine(coffeeMachineSpecs)
            "take" -> takeCoffeeMoney(coffeeMachineSpecs)
            "remaining" -> printCoffeeMachineSpecs(coffeeMachineSpecs)
        }
    } while (action != "exit")

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

fun takeCoffeeMoney(coffeeMachineSpecs: CoffeeMachineSpecs) {
    println("I gave you ${coffeeMachineSpecs.cash}")
    coffeeMachineSpecs.cash = 0
}

fun buyCoffee(coffeeMachineSpecs: CoffeeMachineSpecs, coffeeList: List<CoffeeSpecs>) {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")
    when (readln()) {
        "1" -> makeCoffee(coffeeMachineSpecs, coffeeList[0])
        "2" -> makeCoffee(coffeeMachineSpecs, coffeeList[1])
        "3" -> makeCoffee(coffeeMachineSpecs, coffeeList[2])
    }
}

fun makeCoffee(coffeeMachineSpecs: CoffeeMachineSpecs, coffeeSpecs: CoffeeSpecs) {

    val enoughWater = enoughResources(coffeeMachineSpecs.water, coffeeSpecs.water)
    val enoughMilk = enoughResources(coffeeMachineSpecs.milk, coffeeSpecs.milk)
    val enoughBeans = enoughResources(coffeeMachineSpecs.beans, coffeeSpecs.beans)
    val enoughCups = coffeeMachineSpecs.cups>=1

    if (enoughWater && enoughMilk && enoughBeans && enoughCups) {
        println("I have enough resources, making you a coffee!")
        useResources(coffeeMachineSpecs, coffeeSpecs)
    } else {
        print("Sorry, not enough ")
        when {
            !enoughWater -> println("water!")
            !enoughMilk -> println("milk!")
            !enoughBeans -> println("beans!")
            !enoughCups -> println("cups!")
        }
    }
}

fun useResources(coffeeMachineSpecs: CoffeeMachineSpecs, coffeeSpecs: CoffeeSpecs) {
    coffeeMachineSpecs.water -= coffeeSpecs.water
    coffeeMachineSpecs.milk -= coffeeSpecs.milk
    coffeeMachineSpecs.beans -= coffeeSpecs.beans
    coffeeMachineSpecs.cups--
    coffeeMachineSpecs.cash += coffeeSpecs.cost
}

fun enoughResources(resourceAvailable: Int, resourceNeeded: Int): Boolean {
    return if(resourceNeeded==0) true else resourceAvailable / resourceNeeded >= 1
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

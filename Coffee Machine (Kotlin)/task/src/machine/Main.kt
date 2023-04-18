package machine

class CoffeeSpecs(val water: Int, val milk: Int, val beans: Int, val cost: Int) {}



fun main() {

    val coffeeMachineSpecs = CoffeeMachine()

    do {
        coffeeMachineSpecs.showMessage()
        val action = readln()
        coffeeMachineSpecs.actionEvent(action)

    } while (action != "exit")

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




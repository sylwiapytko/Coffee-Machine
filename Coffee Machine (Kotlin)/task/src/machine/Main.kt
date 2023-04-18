package machine

class CoffeeSpecs(val water: Int, val milk: Int, val beans: Int, val cost: Int) {}
class CoffeeMachineSpecs(var water: Int = 400, var milk: Int = 540, var beans: Int = 120, var cups: Int = 9, var cash: Int = 550) {
    var currentStatus = Status.ACTION_CHOICE
    val coffeeList = mutableListOf<CoffeeSpecs>()

    init {
        val espresso = CoffeeSpecs(250, 0, 16, 4)
        val latte = CoffeeSpecs(350, 75, 20, 7)
        val cappuccino = CoffeeSpecs(200, 100, 12, 6)

        coffeeList.addAll(listOf<CoffeeSpecs>(espresso, latte, cappuccino))
    }

    fun showMessage() {
        when (currentStatus) {
            Status.ACTION_CHOICE -> println("Write action (buy, fill, take, remaining, exit):")
            Status.BUYING -> println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")
//            Status.FILLING -> printFillingDialog()
        }
    }

//    private fun printFillingDialog() {
//        TODO("Not yet implemented")
//    }

    fun actionEvent(input: String) {
        when (currentStatus) {
            Status.ACTION_CHOICE -> makeAction(input)
            Status.BUYING -> buyCoffee(input)


        }
    }

    private fun makeAction(input: String) {
        when (input) {
            "buy" -> changeStatus(Status.BUYING)
            "fill" -> changeStatus(Status.FILLING)
            "take" -> takeCoffeeMoney()
            "remaining" -> printCoffeeMachineSpecs()
        }
    }


    private fun changeStatus(newStatus: Status) {
        currentStatus = newStatus
    }

    fun buyCoffee(input: String) {
        when (input) {
            "1" -> makeCoffee(coffeeList[0])
            "2" -> makeCoffee(coffeeList[1])
            "3" -> makeCoffee(coffeeList[2])
        }
        changeStatus(Status.ACTION_CHOICE)
    }

    fun makeCoffee(coffeeSpecs: CoffeeSpecs) {

        val enoughWater = enoughResources(water, coffeeSpecs.water)
        val enoughMilk = enoughResources(milk, coffeeSpecs.milk)
        val enoughBeans = enoughResources(beans, coffeeSpecs.beans)
        val enoughCups = cups >= 1

        if (enoughWater && enoughMilk && enoughBeans && enoughCups) {
            println("I have enough resources, making you a coffee!")
            useResources(coffeeSpecs)
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

    private fun useResources(coffeeSpecs: CoffeeSpecs) {
        water -= coffeeSpecs.water
        milk -= coffeeSpecs.milk
        beans -= coffeeSpecs.beans
        cups--
        cash += coffeeSpecs.cost
    }

    private fun enoughResources(resourceAvailable: Int, resourceNeeded: Int): Boolean {
        return if (resourceNeeded == 0) true else resourceAvailable / resourceNeeded >= 1
    }

    private fun takeCoffeeMoney() {
        println("I gave you ${cash}")
        cash = 0
    }

    private fun printCoffeeMachineSpecs() {
        println("The coffee machine has:")
        println("${water} ml of water")
        println("${milk} ml of milk")
        println("${beans} g of coffee beans")
        println("${cups} disposable cups")
        println("${cash} money")
    }


}

enum class Status() {
    ACTION_CHOICE,
    BUYING,
    FILLING
}

fun main() {

    val coffeeMachineSpecs = CoffeeMachineSpecs()

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

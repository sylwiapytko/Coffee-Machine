package machine


enum class Status() {
    ACTION_CHOICE,
    BUYING,
    FILLING
}

class CoffeeMachine(var water: Int = 400, var milk: Int = 540, var beans: Int = 120, var cups: Int = 9, var cash: Int = 550) {
    var currentStatus = Status.ACTION_CHOICE
    var fillingStatusNB = 0
    val coffeeList = mutableListOf<CoffeeSpecs>()

    val mapResources = mutableMapOf(
            "water" to "ml of water",
            "milk" to "ml of milk",
            "beans" to "grams of coffee beans",
            "cups" to "disposable cups")

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
            Status.FILLING -> printFillingDialog()
        }
    }

    private fun printFillingDialog() {
        println("Write how many ${Resources.values()[fillingStatusNB].text} you want to add:")
    }

    fun actionEvent(input: String) {
        when (currentStatus) {
            Status.ACTION_CHOICE -> makeAction(input)
            Status.BUYING -> buyCoffee(input)
            Status.FILLING -> fillCoffeeMachine(input)

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

    fun fillCoffeeMachine(input: String) {
        input.toIntOrNull()?.let {
            when (Resources.values()[fillingStatusNB]) {
                Resources.WATER -> water += input.toInt()
                Resources.MILK -> milk += input.toInt()
                Resources.BEANS -> beans += input.toInt()
                Resources.CUPS -> cups += input.toInt();
            }
            fillingStatusNB++
        }

        checkFinishFilling()


    }

    private fun checkFinishFilling() {
        if (fillingStatusNB >= Resources.values().size) {
            fillingStatusNB = 0
            changeStatus(Status.ACTION_CHOICE)
        }
    }


}

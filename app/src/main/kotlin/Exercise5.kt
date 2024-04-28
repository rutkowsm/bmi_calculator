class Exercise5 {
    /**
     * You have a program that counts pizza slices until there’s a whole pizza with 8 slices.
     * Refactor this program in two ways:
     *
     * Use a while loop.
     * Use a do-while loop.
     */

    fun main() {
        var pizzaSlices = 0
        var pizzaUnit:String = "slice"
        var toBe:String = "is"
        // Start refactoring here
        /*
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
        pizzaSlices++
        */
        while (pizzaSlices < 7) {
            pizzaSlices++
            if (pizzaSlices > 1) {
                pizzaUnit = "slices"
                toBe = "are"
            }
            println("There $toBe only $pizzaSlices $pizzaUnit of pizza :(")
        }
        // End refactoring here
        println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")
    }

    fun otherLoop(){
        var pizzaSlices = 0
        var pizzaUnit:String = "slice"
        var toBe:String = "is"
        // Start refactoring here
        do {
            if (pizzaSlices > 1) {
                pizzaUnit = "slices"
                toBe = "are"
            }
            println("There $toBe only $pizzaSlices $pizzaUnit of pizza :(")
            pizzaSlices++
        }
            while (pizzaSlices < 8)
        // End refactoring here
        println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")
    }
}
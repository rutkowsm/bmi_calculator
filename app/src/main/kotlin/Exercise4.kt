class Exercise4 {

    /**
     * Using a when expression, update the following program
     * so that when you input the names of GameBoy buttons, the actions are printed to output.
     *
     * Button | Action
     * ---------------
     * A | Yes
     * B | No
     * X | Menu
     * Y | Nothing
     * Other | There is no such button
     */

    fun main(){
        val button = "A"
        println(
            // Write your code here
            when(button) {
                "A" -> "Yes"
                "B" -> "No"
                "X" -> "Menu"
                "Y" -> "Nothing"
                else -> "There is no such button"
            }
        )
    }
}
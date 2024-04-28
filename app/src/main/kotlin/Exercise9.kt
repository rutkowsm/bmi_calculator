class Exercise9 {

    /**
     *
     * Write a function that takes an Int value and an action (a function with type () -> Unit)
     * which then repeats the action the given number of times.
     * Then use this function to print “Hello” 5 times.
     *
     */

    fun repeatN(n: Int, action: () -> Unit) {
    // Write your code here
        for (i in 1..n){
            action()
        }
    }
    fun main() {
    // Write your code here
        repeatN(5){
            println("Hello")
        }
    }
}
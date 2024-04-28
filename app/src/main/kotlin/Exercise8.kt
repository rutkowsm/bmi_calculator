import kotlin.math.PI

class Exercise8 {

    /**
     *
     * Write a function called circleArea that takes the radius of a circle in integer format as a parameter and outputs the area of that circle.
     * In this exercise, you import a package so that you can access the value of pi via PI.
     *
     */

    fun circleArea(radius:Int):Double {
        return PI * (radius * radius)
    }

    fun main(){
        println(circleArea(2))
    }
}
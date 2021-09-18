
class MyList<T> {
    var nextEl: MyList<T>? = null
    var value: T? = null

    fun hasNext(): Boolean {
        return nextEl != null
    }

    fun add(value: T) {
        when {
            this.value == null -> {
                // println("$value this.value = null")
                this.value = value
            }
            nextEl != null -> {
                // println("$value nextEl != null")
                nextEl!!.add(value)
            }
            else -> {
                // println("$value nextEl == null")
                nextEl = MyList()
                nextEl!!.value = value
            }
        }
    }

    fun set(idx: Int, value: T): Boolean {
        if (idx == 0) {
            this.value = value
            return true
        } else {
            if (nextEl == null) return false
            return nextEl!!.set(idx - 1, value)
        }
    }

    fun contains(value: T): Boolean {
        // println("this.value - ${this.value} ; value  - ${value}")
        if (this.value == value) {
            return true
        } else {
            if (nextEl == null) return false
            return nextEl!!.contains(value)
        }
    }

    fun remove(value: T): Boolean {
        if (this.value == value) {
            this.value = nextEl?.value
            nextEl = nextEl?.nextEl
            return true
        } else {
            if (this.nextEl == null) return false
            return nextEl!!.remove(value)
        }
    }

    fun indexOf(value: T): Int?{
        if (this.value == null) return -1
        return if(this.value == value) 0
        else nextEl?.indexOf(value, 1)
    }

    private fun indexOf(value: T, idx: Int): Int? {
        if (value == null) return -1
        return if(this.value == value) idx
        else nextEl?.indexOf(value, idx+1)
    }
}

fun printList(list: MyList<Int>) {
    var pt = list
    do {
        val a = pt.value
        println(a)
        if (pt.nextEl == null) break
        pt = pt.nextEl!!
    } while (pt.value != null)
}

fun main() {

    val list = MyList<Int>()

    list.add(3)
    list.add(7)
    list.add(8)

    printList(list)

    println("set list[2] = 30")

    list.set(2, 30)

    printList(list)

    println("list contains 2 - ${list.contains(2)}")
    println("list contains 30 - ${list.contains(30)}")

    println("removes list = 30 ")
    list.remove(30)
    printList(list)

    list.add(14)
    list.add(9)
    list.remove(3)

    println()
    printList(list)

    println("idx of 30 - ${list.indexOf(30)}")
    println("idx of 14 - ${list.indexOf(14)}")
    println("idx of 7 - ${list.indexOf(7)}")
}


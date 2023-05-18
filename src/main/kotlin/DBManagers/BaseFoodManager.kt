package src.DBManagers

abstract class BaseFoodManager<T> {
    protected var items = LinkedHashSet<T>()

    fun addItem(foodItem: T) {
        this.items.add(foodItem)
    }

    fun removeItem(foodItem: T): Boolean {
        if (foodItemExists(foodItem)) {
            items.remove(foodItem)
            return true
        } else {
            return false
        }
    }

    fun foodItemExists(foodItem: T): Boolean {
        return foodItem in items
    }

    fun getCollection(): LinkedHashSet<T>{
        return this.items
    }

}
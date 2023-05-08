package src.DBManagers

import ActualFoodItem
import java.time.LocalTime

class ActualFoodManager : BaseFoodManager<ActualFoodItem>() {
    private var lastCleanTime = LocalTime.now()
    fun cleanFoodItems() {
        val currentTime = LocalTime.now()
        if (currentTime.isAfter(lastCleanTime.withHour(0).withMinute(0).withSecond(0))) {
            this.items.clear()
            lastCleanTime = currentTime
        }
    }
}
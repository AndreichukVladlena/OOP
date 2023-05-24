import com.example.Entities.User
import com.example.const.maleList
class InterfaceService() {
    fun chooseResult(user:User): IResult? {
        if (user.getAge()>=18 && user.getMale()==maleList[0]) {
            return MaleResult(user)
        }
        else if (user.getAge()>=18 && user.getMale()==maleList[1]){
            return FemaleResult(user)
        }
        else if(user.getAge()<18){
            return ChildResult(user)
        }
        else {return null}
    }
}
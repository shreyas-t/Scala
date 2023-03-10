package tutorial

object Livelock extends App{
  case class Friend(name: String){
    val side = "right"

    def switchSide() = {
      if(side == "right") "left"
      else "right"
    }

    def pass(other: Friend) = {
      while(this.side == other.side){
        println(s"$this: Oh please $other feel free to pass")
        switchSide()
        Thread.sleep(1000)
      }
    }

  }

  val rhea = Friend("Rhea")
  val shre = Friend("Shreyas")
  new Thread(() => rhea.pass(shre)).start()
  new Thread(() => shre.pass(rhea)).start()
}

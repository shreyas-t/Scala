package tutorial



object Deadlock extends App{

  case class Friend(name: String){
    def bow(other: Friend): Unit = {
      this.synchronized{
        println(s"$this: I'm bowing to my friend $other")
        other.rise(this)
        println(s"$this: My friend $this has risen")
      }
    }

    def rise(other: Friend): Unit = {
      this.synchronized {
        println(s"$this: I'm rising to my friend $other")
      }
    }


  }

  val rhea = Friend("Rhea")
  val shre = Friend("Shreyas")
  new Thread(() => rhea.bow(shre)).start()
  new Thread(() => shre.bow(rhea)).start()
}

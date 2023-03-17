package tutorial

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.Try

object LastFuture extends App{
  def lastFuture(fa: Future[Int], fb: Future[Int]): Future[Int] = {
    val bothPromise = Promise[Int]()
    val lastPromise = Promise[Int]()

    val checkAndComplete = (result: Try[Int]) =>
      if(!bothPromise.tryComplete(result))
        lastPromise.complete(result)


    fa.onComplete(checkAndComplete)
    fb.onComplete(checkAndComplete)

    lastPromise.future
  }

  val fast = Future {
    Thread.sleep(100)
    42
  }

  val slow = Future {
    Thread.sleep(200)
    45
  }

  lastFuture(fast, slow).foreach(println)

  Thread.sleep(1000)
}

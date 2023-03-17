package tutorial

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}

object FirstFuture extends App{
  def firstFuture(fa: Future[Int], fb: Future[Int]): Future[Int] ={
    val promise = Promise[Int]()

    fa.onComplete(promise.tryComplete)
    fb.onComplete(promise.tryComplete)

    promise.future
  }

  val fast = Future{
    Thread.sleep(100)
    42
  }

  val slow = Future {
    Thread.sleep(100)
    45
  }

  firstFuture(fast,slow).foreach(println)

  Thread.sleep(1000)
}

package com.hogly.actors

import akka.actor.{Actor, ActorSystem, Props}

class MyActor extends Actor {

  override def receive(): Receive = {
    case "Hi" =>
      val secondRef = context.actorOf(Props.empty, "second-actor")
      println(s"Second: $secondRef")
  }
}

object ActorsApplication extends App {
  val system = ActorSystem.create("testSystem")
  val firstRef = system.actorOf(Props[MyActor], "first-actor")
  firstRef ! "Hi"
  println(s"First: $firstRef")
}

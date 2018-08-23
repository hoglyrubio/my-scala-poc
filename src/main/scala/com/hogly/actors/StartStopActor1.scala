package com.hogly.actors

import akka.actor.{Actor, ActorSystem, Props}

class StartStopActor1 extends Actor {

  override def preStart(): Unit = {
    println(s"First started $self")
    context.actorOf(Props[StartStopActor2], "second")
  }

  override def postStop(): Unit = println(s"first stopped $self")

  override def receive: Receive = {
    case "stop" => context.stop(self)
  }
}

class StartStopActor2 extends Actor {
  override def preStart(): Unit = println(s"second started $self")
  override def postStop(): Unit = println(s"second stopped $self")
  override def receive: Receive = Actor.emptyBehavior
}

object MyApp extends App {
  val system = ActorSystem.create("stopping-actors")
  val first = system.actorOf(Props[StartStopActor1], "first")
  first ! "stop"
}
package gcd

import chisel3._
import chisel3.util.Decoupled
import org.scalatest.flatspec.AnyFlatSpec
import chiseltest._
import chiseltest.formal._

class UartRx(width: Int) extends Module {
  val en = IO(Input(Bool()))
  val data = IO(Input(UInt(width.W)))


}

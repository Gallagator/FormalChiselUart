package uart

import chisel3._
import chiseltest._
import chiseltest.formal._


class UartRx(width: Int, parity: Option[Parity], stop_bits: StopBits)
    extends Module {
  val en = IO(Input(Bool()))
  val data = IO(Input(UInt(width.W)))
  val out = IO(Output(Bool()))

  when(en) {
    
  }
}

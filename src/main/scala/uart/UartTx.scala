package uart

import chisel3._
import chiseltest._
import chiseltest.formal._
import chisel3.util._

class UartTx(width: Int, parity: Option[Parity], stopBits: StopBits)
    extends Module {
  val en = IO(Input(Bool()))
  val data = IO(Input(UInt(width.W)))
  val out = IO(Output(Bool()))

  val (shiftWidth, stopData) = stopBits match {
    case One() => (width + 1, "b1".U)
    case Two() => (width + 2, "b11".U)
  }

  val idle = RegInit(Bool(), true.B)
  val outbuf = RegInit(Bool(), true.B)
  val shiftreg = Reg(UInt((shiftWidth).W))
  val bitIdx = Counter(shiftWidth)

  when(en) {
    when(idle) {
      /* start phase */
      idle := false.B
      outbuf := false.B // Start bit
      shiftreg := Cat(stopData, data)
    }.otherwise {
      outbuf := shiftreg(0)
      shiftreg := shiftreg >> 1
      val wrap = bitIdx.inc()
      when(wrap) {
        idle := true.B
      }
    }
  }
  out := outbuf

  /* Formal */
  when(en && idle) {
    assert(bitIdx.value === 0.U)
    assert(out === 1.U)
  }
  /* After every non-idle clock, shift out uart bit. */
  when(past(en) && !past(idle)) {
    assert(out === past(shiftreg(0)))
  }
  when(idle) {
    assert(out === true.B)
  }

  // TODO implement parity. Do formal for stop bits and parity
}

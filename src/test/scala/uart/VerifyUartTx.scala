package uart

import chisel3._
import chiseltest._
import chiseltest.formal._
import org.scalatest.flatspec.AnyFlatSpec
import chisel3.util._


class VerifyUartTx extends AnyFlatSpec with ChiselScalatestTester with Formal {
  "UartTx" should "pass bmc" in {
    verify(new UartTx(8, None, One()), Seq(BoundedCheck(15)))
  }
}


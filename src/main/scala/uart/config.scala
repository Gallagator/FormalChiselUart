package uart

trait Parity

case class Odd() extends Parity
case class Even() extends Parity

trait StopBits

case class One() extends StopBits
case class Two() extends StopBits

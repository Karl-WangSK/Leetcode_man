package basic.netty

trait RpcMsg extends Serializable

case class registerdMsg(info: String) extends RpcMsg

case class HeartBeat() extends RpcMsg

case class ShutDown() extends RpcMsg

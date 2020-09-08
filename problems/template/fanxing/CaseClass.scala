package template.fanxing

abstract  class Father[+T]()

case class Test[T](id:String, list:List[T]) extends Father

case class Test2[T](id:String, set:Set[T]) extends Father

class CaseClass extends generic {


  def sss(f:Father[String]): Unit ={
    f match {
      case a @ Test(id,_)=>
        println(a.id)
      case _ =>
        println("null")
    }
  }



  override def trans[T, U](s: Father[T], a: Father[U]): Unit = {

    s match {
      case dd @ Test(id:String,list:List[T])  =>
        println(dd.list(0))

      case bb @ Test2(id:String,set:Set[U]) =>
        println(bb.set(0))

      case _ => println("")
    }


  }
}

object CaseClass{

  def main(args: Array[String]): Unit = {
    val clazz = new CaseClass

    clazz.trans[String,String](Test("1",List("2","3")),Test("2",List(1)))

  }
}

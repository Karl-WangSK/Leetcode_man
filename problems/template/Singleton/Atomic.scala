package template.Singleton

import java.util.concurrent.atomic.AtomicReference

class Atomic {
  private val allowMultipleContexts: Boolean = false

  // In order to prevent multiple Contexts from being active at the same time, mark this
  // context as having started construction.
  // NOTE: this must be placed at the beginning of the Contexts constructor.
  Atomic.setActiveContext(this, allowMultipleContexts)


}

object Atomic{
  private final val CONTEXT_CONSTRUCTOR_LOCK=new Object()

  //Set this class as atomic
  private val activeContext: AtomicReference[Atomic] = new AtomicReference[Atomic](null)

  //Get new Instance or get latest one
  def getOrElse(): Atomic ={
    CONTEXT_CONSTRUCTOR_LOCK.synchronized{
      if (activeContext.get()==null){
        setActiveContext(new Atomic(), allowMultipleContexts = false)
      }else{
        println("Using an existing Context; some configuration may not take effect.")
      }
      activeContext.get()
    }
  }

  /**
    * Set active Context if this class is the first time to be called
    * Else throw exception
    * @param atomic
    * @param allowMultipleContexts
    */
  def setActiveContext(atomic:Atomic,allowMultipleContexts:Boolean): Unit ={
    CONTEXT_CONSTRUCTOR_LOCK.synchronized{
      assertNoOtherContextIsRunning(atomic, allowMultipleContexts)
      activeContext.set(atomic)
    }
  }

  /**
    * Ensure there is no other Context is running and global unique
    * @param atomic
    * @param allowMultipleContexts
    */
  def assertNoOtherContextIsRunning(atomic:Atomic,allowMultipleContexts:Boolean): Unit ={
    CONTEXT_CONSTRUCTOR_LOCK.synchronized{
      Option(activeContext.get()).filter(_ ne atomic).foreach{atomic=>
        val exception: Exception = new IllegalStateException("error")
        if (allowMultipleContexts){
          println("Multiple running KuduContexts detected in the same JVM!", exception)
        }else{
          throw new IllegalArgumentException
        }
      }
    }
  }


  def main(args: Array[String]): Unit = {
    val atomic = Atomic.getOrElse()
    val atomic2 = Atomic.getOrElse()
  }


}

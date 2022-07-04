import vegas.spec.Spec.MarkEnums.Bar

import scala.collection.mutable
import vegas._
import Main.numberOfAppsByCategory
import Main.numberOfAppsByType
import Main.numberOfAppsByAndroidVersion
import org.apache.spark.rdd.RDD

object Plot {

  def main(args: Array[String]): Unit = {
    // Some examples... the graphs are in the GUI
    plot_graph(numberOfAppsByCategory, "Category", "Number of Apps")
    plot_graph(numberOfAppsByType, "Type", "Number of Apps")
    plot_graph(numberOfAppsByAndroidVersion, "Android Version", "Number of Apps")
  }

  // ***************************************
  // ** Graphic Plot function **
  // ***************************************

  def plot_graph(dataMap: RDD[(String, Int)], k: String, v: String): Unit = {
    val plot = Vegas(k + " " + v, width = 600.0, height = 400.0).
      withData(
        dataMap.collect().map(x => Map(k -> x._1, v -> x._2))
      ).
      encodeX(k, Nom).
      encodeY(v, Quant).
      mark(Bar)
    plot.window.show
  }
}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

import scala.collection.mutable

object FileIO extends {

  val spark: SparkSession = SparkSession.builder()
    .appName("Spark GooglePlayStore Session")
    .master("local[2]")
    .getOrCreate()
  Logger.getRootLogger.setLevel(Level.ERROR)

  def readFile(filePath: String): RDD[App] = {
    val originalRDD = spark.sparkContext.textFile(filePath)
    val apps = originalRDD.map(row => row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"))

    // Get the apps
    val appList = apps.filter(a => a.length == 13).filter(a => !a.contains("NaN")).map(a => new App(a(0), a(1), a(2).toDouble,
      a(3).toInt, a(4), a(5), a(6), a(7), a(8), a(9), a(10), a(11), a(12)))

    appList
  }
}
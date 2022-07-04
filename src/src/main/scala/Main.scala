import FileIO.readFile
import org.apache.spark.rdd.RDD

object Main {

  def main(args: Array[String]): Unit = {

    println("########################")
    println("Total number of apps" + appList.count())
    println("########################")

    println("\n########################")
    println("       CATEGORIES")
    println("########################")
    println("Total number of categories: " + numberOfCategories)
    println("-----------------------")
    println("Number of apps by categories")
    numberOfAppsByCategory.foreach(a => println(a))


    println("\n########################")
    println("       TYPE")
    println("########################")
    println("Total number of types: " + numberOfTypes)
    println("-----------------------")
    println("Number of apps by types")
    numberOfAppsByType.foreach(a => println(a))

    println("\n########################")
    println("       ANDROID VERSION")
    println("########################")
    println("Total number of Android Versions: " + numberOfAndroidVersions)
    println("-----------------------")
    println("Number of apps by Android Versions")
    numberOfAppsByAndroidVersion.foreach(a => println(a))

    println("\n########################")
    println("       INSTALLS")
    println("########################")
    println("Total number of installs: " + numberOfInstalls)
    println("-----------------------")
    println("Number of apps by installs")
    numberOfAppsByInstalls.foreach(a => println(a))

    println("\n########################")
    println("       INSTALLS")
    println("     (AS INTEGERS)")
    println("########################")
    println("Total number of installs (using Integers): " + numberOfInstallsInteger)
    println("-----------------------")
    println("Number of apps by installs (using Integers)")
    numberOfAppsByInstallsInteger.foreach(a => println(a))

    println("\n-----------------------")
    println("Most installed category: " + mostDownloadedCategory.keys.max() + ", with " + mostDownloads + " downloads")

    println("\nNumber of paid apps with the given category, with more than 10,000 installs")
    mostInstallsPaidAppsCategory.foreach(a => println(a))
    println("\nNumber of free apps with the given category, with more than 10,000 installs")
    mostInstallsFreeAppsCategory.foreach(a => println(a))

    println("\nThere are " + bestRatingApps.count() + " apps with rating higher than 4.8")

    println("\nNumber of apps with the given category, with rating higher than 4.8")
    categoryWithBetterRating.foreach(a => println(a))

    println("\nHypothesis 1")
    println("------------------------------")
    println("\nIf the price is greater than $50, installs are lower than 1000")
    println(conclusion1)

    println("\nHypothesis 2")
    println("------------------------------")
    println("\nIf the rating is greater than 4.0, installs are over 10,000")
    println(conclusion2)

    println("\nHypothesis 3")
    println("------------------------------")
    println("\nIf the number of reviews is higher than 300, installs are over 10,000")
    println(conclusion3)


    println("\nHypothesis 4")
    println("------------------------------")
    println("\n If the size is greather than 50MB, installs are less than 50000")
    println(conclusion4)

    println("\nHypothesis 5")
    println("------------------------------")
    println("\n If the app is for adults, installs are lower than 10.000")
    println(conclusion5)

    println("\nHypothesis 6")
    println("------------------------------")
    println("\n If the rating is over 4.0, installs are over 500.000")
    println(conclusion6)

    println("\nHypothesis 7")
    println("------------------------------")
    println("\n If the apps category is Dating, its free")
    println(conclusion7)

    println("\nHypothesis 8")
    println("------------------------------")
    println("\n If the apps category is Social, its free")
    println(conclusion8)

    println("\nExample APP")
    buildExampleAppString()
    println(exampleString)
  }

  val appList: RDD[App] = readFile("src/main/resources/googleplaystore.csv")
  var exampleString: String = ""

  def buildExampleAppString(): Unit = {
    appList.filter(a => a.name == "I am rich").filter(a => a.category == "LIFESTYLE").foreach(a => exampleString += "#######   App --> " + a.name + "   #######" +
      "\n     Category          --> " + a.category +
      "\n     Rating            --> " + a.rating +
      "\n     Reviews           --> " + a.reviews +
      "\n     Size              --> " + a.size +
      "\n     Installs          --> " + a.installs +
      "\n     AppType           --> " + a.appType +
      "\n     Price             --> " + a.price +
      "\n     Content Rating    --> " + a.contentRating +
      "\n     Genres            --> " + a.genres +
      "\n     Last Updated      --> " + a.lastUpdated +
      "\n     Current Version   --> " + a.currentVer +
      "\n     Android Version   --> " + a.androidVer)
  }


  //      ***************************************
  //      ** Functions over the category       **
  //      ***************************************

  val numberOfAppsByCategory: RDD[(String, Int)] = appList.map(app => (app.category, 1)).reduceByKey(_ + _)
  val numberOfCategories: Long = numberOfAppsByCategory.keys.count()

  val mostDownloads: Integer = numberOfAppsByCategory.values.max()
  val mostDownloadedCategory: RDD[(String, Int)] = numberOfAppsByCategory.filter(a => a._2 == mostDownloads)

  //      ***************************************
  //      ** Functions over the type           **
  //      ***************************************

  val numberOfAppsByType: RDD[(String, Int)] = appList.map(app => (app.appType, 1)).reduceByKey(_ + _)
  val numberOfTypes: Long = numberOfAppsByType.keys.count()

  //      ***************************************
  //      ** Functions over the Android Version**
  //      ***************************************

  val numberOfAppsByAndroidVersion: RDD[(String, Int)] = appList.map(app => (app.androidVer, 1)).reduceByKey(_ + _)
  val numberOfAndroidVersions: Long = numberOfAppsByAndroidVersion.keys.count()

  //      ***************************************
  //      ** Functions over the installs       **
  //      ***************************************

  val numberOfAppsByInstalls: RDD[(String, Int)] = appList.map(app => (app.installs, 1)).reduceByKey(_ + _)
  val numberOfInstalls: Long = numberOfAppsByInstalls.keys.count()

  //      ***************************************
  //      ** Functions over the installs (INT) **
  //      ***************************************

  val numberOfAppsByInstallsInteger: RDD[(Int, Int)] = appList.map(app => (app.installs.replace(",", "").
    replace("+", "").replace("\"", "").toInt, 1)).reduceByKey(_ + _)

  val numberOfInstallsInteger: Long = numberOfAppsByInstallsInteger.keys.count()

  //      *************************************************
  //      ** Mix types and categories with installs      **
  //      *************************************************

  // Take the paid apps --> take the most installed ones (10,000+ installs) -->
  // --> get the most installed paid app categories

  val paidApps: RDD[App] = appList.filter(a => a.appType == "Paid")
  val mostInstallsPaidApps: RDD[App] = paidApps.filter(a => a.installs == "\"10,000+\"")
  val mostInstallsPaidAppsCategories: RDD[(String, Int)] = mostInstallsPaidApps.map(app => (app.category.replace(",", "").
    replace("+", "").replace("\"", ""), 1)).reduceByKey(_ + _)


  val maxInstallsPaid: Int = mostInstallsPaidAppsCategories.map(_._2).max()
  val mostInstallsPaidAppsCategory: RDD[(String, Int)] = mostInstallsPaidAppsCategories.filter(_._2 == maxInstallsPaid)


  // Take the free apps --> take the most installed ones (10,000+ installs) -->
  // --> get the most installed free app categories

  val freeApps: RDD[App] = appList.filter(a => a.appType == "Free")
  val mostInstallsFreeApps: RDD[App] = freeApps.filter(a => a.installs == "\"10,000+\"")
  val mostInstallsFreeAppsCategories: RDD[(String, Int)] = mostInstallsFreeApps.map(app => (app.category.replace(",", "").
    replace("+", "").replace("\"", ""), 1)).reduceByKey(_ + _)


  val maxInstallsFree: Int = mostInstallsFreeAppsCategories.map(_._2).max()
  val mostInstallsFreeAppsCategory: RDD[(String, Int)] = mostInstallsFreeAppsCategories.filter(_._2 == maxInstallsFree)

  // ----------------------------------------------------------

  val bestRatingApps: RDD[App] = appList.filter(a => a.rating > 4.8)
  val bestRatingAppsCategories: RDD[(String, Int)] = bestRatingApps.map(app => (app.category.replace(",", "").
    replace("+", "").replace("\"", ""), 1)).reduceByKey(_ + _)
  val bestRatingAppsAux: Int = bestRatingAppsCategories.map(a => a._2).max()
  val categoryWithBetterRating: RDD[(String, Int)] = bestRatingAppsCategories.filter(_._2 == bestRatingAppsAux)


  //      ********************************************
  //      *                 HYPOTHESIS               *
  //      ********************************************

  //      *******************************
  //      **       1st HYPOTHESIS      **
  //      *******************************
  //      If the price is greater than $50, installs are lower than 1000

  val appsPriceGreater50: RDD[App] = appList.filter(a => a.appType == "Paid").filter(a => a.price.drop(1).toDouble > 50)

  val appsPriceGreater50LessOr1000Installs: RDD[App] = appsPriceGreater50.filter(a => a.installs.replace(",", "")
    .replace("+", "")
    .replace("\"", "").toInt <= 1000)

  val conclusion1: Double = appsPriceGreater50LessOr1000Installs.count() * 1.0f / appsPriceGreater50.count()


  //      *******************************
  //      **       2nd HYPOTHESIS      **
  //      *******************************
  //      If the rating is greater than 4.0, installs are over 10,000

  val appsRatingGreaterOr4: RDD[App] = appList.filter(a => a.rating >= 4.0)
  val appsRatingGreaterOr4MoreOr10000Installs: RDD[App] = appsRatingGreaterOr4.filter(a => a.installs.replace(",", "")
    .replace("+", "")
    .replace("\"", "").toInt >= 10000)

  val conclusion2: Double = appsRatingGreaterOr4MoreOr10000Installs.count() * 1.0f / appsRatingGreaterOr4.count()

  //      *******************************
  //      **       3rd HYPOTHESIS      **
  //      ********************************
  //      If the number of reviews is higher than 300, installs are over 10,000


  val appsReviewsGreaterOr300: RDD[App] = appList.filter(a => a.reviews >= 300)
  val appsReviewsGreaterOr300MoreOr10000Installs: RDD[App] = appsReviewsGreaterOr300.filter(a => a.installs.replace(",", "")
    .replace("+", "")
    .replace("\"", "").toInt >= 10000)

  val conclusion3: Double = appsReviewsGreaterOr300MoreOr10000Installs.count() * 1.0f / appsReviewsGreaterOr300.count()


  //      *******************************
  //      **       4th HYPOTHESIS      **
  //      *******************************
  //      If the size is greather than 50MB, installs are less than 50000


  val appsSizeGreaterOr50: RDD[App] = appList.filter(a => a.size != "Varies with device" && a.size.replace("M", "").replace("k", "").toDouble >= 50)
  val appsSizeGreaterOr50LessOr50000Installs: RDD[App] = appsSizeGreaterOr50.filter(a => a.installs.replace(",", "")
    .replace("+", "")
    .replace("\"", "").toInt <= 50000
  )

  val conclusion4: Double = appsSizeGreaterOr50LessOr50000Installs.count() * 1.0f / appsSizeGreaterOr50.count()


  //      *******************************
  //      **       5th HYPOTHESIS      **
  //      *******************************
  //      If the app is for adults, installs are lower than 10.000

  val appsContentRatingMature: RDD[App] = appList.filter(a => a.contentRating == "Mature 17+")
  val appsContentRatingMatureLessOr10000Installs: RDD[App] = appsContentRatingMature.filter(a => a.installs.replace(",", "")
    .replace("+", "")
    .replace("\"", "").toInt <= 10000
  )

  val conclusion5: Double = appsContentRatingMatureLessOr10000Installs.count() * 1.0f / appsContentRatingMature.count()


  //      *******************************
  //      **       6th HYPOTHESIS      **
  //      *******************************
  //      If the rating is over 4.0, installs are over 500.000")

  val appsRatingGreaterOr4MoreOr500000Installs: RDD[App] = appsRatingGreaterOr4.filter(a => a.installs.replace(",", "")
    .replace("+", "")
    .replace("\"", "").toInt >= 500000)

  val conclusion6: Double = appsRatingGreaterOr4MoreOr500000Installs.count() * 1.0f / appsRatingGreaterOr4.count()

  //      *******************************
  //      **       7th HYPOTHESIS      **
  //      *******************************
  //      If the apps category is Dating, its free

  val appsCategoryDating: RDD[App] = appList.filter(a => a.category == "DATING")
  val appsCategoryDatingAppTypeFree: RDD[App] = appsCategoryDating.filter(a => a.appType == "Free")

  val conclusion7: Double = appsCategoryDatingAppTypeFree.count() * 1.0f / appsCategoryDating.count()


  //      *******************************
  //      **       8th HYPOTHESIS      **
  //      *******************************
  //      If the apps category is Social, its free
  val appsCategorySocial: RDD[App] = appList.filter(a => a.category == "SOCIAL")
  val appsCategorySocialAppTypeFree: RDD[App] = appsCategorySocial.filter(a => a.appType == "Free")

  val conclusion8: Double = appsCategorySocialAppTypeFree.count() * 1.0f / appsCategorySocial.count()
}
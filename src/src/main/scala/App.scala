class App(val name: String, val category: String, val rating: Double, val reviews: Integer,
          val size: String, val installs: String, val appType: String, val price: String,
          val contentRating: String, val genres: String, val lastUpdated: String,
          val currentVer: String, val androidVer: String) {

  def show(): Unit = {
    println("#######   App --> " + this.name + "   #######")
    println("     Category          --> " + this.category)
    println("     Rating            --> " + this.rating)
    println("     Reviews           --> " + this.reviews)
    println("     Size              --> " + this.size)
    println("     Installs          --> " + this.installs)
    println("     AppType           --> " + this.appType)
    println("     Price             --> " + this.price)
    println("     Content Rating    --> " + this.contentRating)
    println("     Genres            --> " + this.genres)
    println("     Last Updated      --> " + this.lastUpdated)
    println("     Current Version   --> " + this.currentVer)
    println("     Android Version   --> " + this.androidVer)
  }

  def showString(): String = {
    val str = "#######   App --> " + this.name + "   #######" +
      "\n     Category          --> " + this.category +
      "\n     Rating            --> " + this.rating +
      "\n     Reviews           --> " + this.reviews +
      "\n     Size              --> " + this.size +
      "\n     Installs          --> " + this.installs +
      "\n     AppType           --> " + this.appType +
      "\n     Price             --> " + this.price +
      "\n     Content Rating    --> " + this.contentRating +
      "\n     Genres            --> " + this.genres +
      "\n     Last Updated      --> " + this.lastUpdated +
      "\n     Current Version   --> " + this.currentVer +
      "\n     Android Version   --> " + this.androidVer
    str
  }
}
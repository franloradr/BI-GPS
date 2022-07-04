import Main.{numberOfAppsByAndroidVersion, numberOfAppsByCategory, numberOfAppsByInstalls, numberOfAppsByType}
import javax.swing.ImageIcon

import scala.swing.{Button, FlowPanel, Frame, Label, MainFrame, SimpleSwingApplication}
import scala.swing.ListView._
import scala.swing._
import scala.swing.event._

object GUI extends SimpleSwingApplication {

  def top: Frame = new MainFrame {
    title = "Google PlayStore Analytics"
    resizable = false
    menuBar = new MenuBar {
      resizable = false
      contents += new Menu("Opciones") {
        contents += new MenuItem(Action("Centrar ventana") {
          centerOnScreen()
        })
        contents += new MenuItem(Action("Cerrar") {
          close()
        })

      }
    }

    /*
     * The root component in this frame is a panel with a border layout.
     */
    contents = new BorderPanel {

      import BorderPanel.Position._

      var reactLive = false
      resizable = false
      font = Font("Arial", Font.Bold, 15)


      val tabs: TabbedPane = new TabbedPane {

        import TabbedPane._

        val home: FlowPanel = new FlowPanel {
          val homeIcon = new Label()
          homeIcon.icon = new ImageIcon("src/main/resources/icon.png")
          val homeETSIIIcon = new Label()
          homeETSIIIcon.icon = new ImageIcon("src/main/resources/etsii.png")
          val homeAuthors = new TextArea()
          homeAuthors.background = new Color(238, 238, 238)
          val homeFont: Font = Font("Monaco", Font.Bold, 20)
          homeAuthors.font = homeFont
          homeAuthors.background = new Color(238, 238, 238)
          homeAuthors.text = "Trabajo realizado por: \n     ·) Daniel Moreno Soto \n     ·) Francisco Jesús Belmonte Pintre \n     ·) Francisco José Alonso Parejo"
          contents += homeETSIIIcon
          contents += homeIcon
          contents += homeAuthors
        }

        val graphs: FlowPanel = new FlowPanel {
          val graphIcon = new Label()
          graphIcon.icon = new ImageIcon("src/main/resources/graph.png")
          val button1 = new Button(Action("Numero de apps por categoría") {
            Plot.plot_graph(numberOfAppsByCategory, "Category", "Number of Apps")
          })
          val button2 = new Button(Action("Numero de apps por tipo (pago o gratis)") {
            Plot.plot_graph(numberOfAppsByType, "Type", "Number of Apps")
          })
          val button3 = new Button(Action("Numero de apps por instalaciones") {
            Plot.plot_graph(numberOfAppsByInstalls, "Installs", "Number of Apps")
          })
          val button4 = new Button(Action("Numero de apps por versiones de Android") {
            Plot.plot_graph(numberOfAppsByAndroidVersion, "Android Version", "Number of Apps")
          })


          val buttonDimension: Dimension = new Dimension(400, 20)

          button1.preferredSize = buttonDimension
          button2.preferredSize = buttonDimension
          button3.preferredSize = buttonDimension
          button4.preferredSize = buttonDimension

          contents += graphIcon
          contents += button1
          contents += button2
          contents += button3
          contents += button4
        }


        val hypothesis: FlowPanel = new FlowPanel {
          val hypIcon = new Label()
          hypIcon.icon = new ImageIcon("src/main/resources/hypothesis.png")
          val hypothesisTextField1 = new TextArea()
          val hypothesisTextField2 = new TextArea()
          val hypothesisTextField3 = new TextArea()
          val hypothesisTextField4 = new TextArea()
          val hypothesisTextField5 = new TextArea()
          val hypothesisTextField6 = new TextArea()
          val hypothesisTextField7 = new TextArea()
          val hypothesisTextField8 = new TextArea()
          hypothesisTextField1.editable = false
          hypothesisTextField2.editable = false
          hypothesisTextField3.editable = false
          hypothesisTextField4.editable = false
          hypothesisTextField5.editable = false
          hypothesisTextField6.editable = false
          hypothesisTextField7.editable = false
          hypothesisTextField8.editable = false
          val dimensions = new Dimension(550, 70)
          hypothesisTextField1.preferredSize = dimensions
          hypothesisTextField2.preferredSize = dimensions
          hypothesisTextField3.preferredSize = dimensions
          hypothesisTextField4.preferredSize = dimensions
          hypothesisTextField5.preferredSize = dimensions
          hypothesisTextField6.preferredSize = dimensions
          hypothesisTextField7.preferredSize = dimensions
          hypothesisTextField8.preferredSize = dimensions

          val hypothesis1: String = "Hipótesis 1: \n Si el precio es mayor de 50$, el número de instalaciones está por debajo de 1.000: \n Se cumple en el " + (Main.conclusion1 * 100).toString.take(5) + "% de los casos"
          val hypothesis2: String = "Hipótesis 2: \n Si el rating es mayor de 4.0, el número de instalaciones está por encima de 10.000: \n Se cumple en el " + (Main.conclusion2 * 100).toString.take(5) + "% de los casos"
          val hypothesis3: String = "Hipótesis 3: \n Si el número de reviews es mayor de 300, el número de instalaciones está por encima de 10.000: \n Se cumple en el " + (Main.conclusion3 * 100).toString.take(5) + "% de los casos"
          val hypothesis4: String = "Hipótesis 4: \n Si el tamaño es mayor de 50MB, el número de instalaciones está por debajo de 50.000: \n Se cumple en el " + (Main.conclusion4 * 100).toString.take(5) + "% de los casos"
          val hypothesis5: String = "Hipótesis 5: \n Si la app es para adultos, el número de instalaciones está por debajo de 10.000: \n Se cumple en el " + (Main.conclusion5 * 100).toString.take(5) + "% de los casos"
          val hypothesis6: String = "Hipótesis 6: \n Si el rating es mayor de 4.0, el número de instalaciones está por encima de 500.000: \n Se cumple en el " + (Main.conclusion6 * 100).toString.take(5) + "% de los casos"
          val hypothesis7: String = "Hipótesis 7: \n Si la app es de citas, es gratis: \n Se cumple en el " + (Main.conclusion7 * 100).toString.take(5) + "% de los casos"
          val hypothesis8: String = "Hipótesis 8: \n Si la app es social, es gratis: \n Se cumple en el " + (Main.conclusion8 * 100).toString.take(5) + "% de los casos"

          hypothesisTextField1.text = hypothesis1
          hypothesisTextField2.text = hypothesis2
          hypothesisTextField3.text = hypothesis3
          hypothesisTextField4.text = hypothesis4
          hypothesisTextField5.text = hypothesis5
          hypothesisTextField6.text = hypothesis6
          hypothesisTextField7.text = hypothesis7
          hypothesisTextField8.text = hypothesis8
          contents += hypIcon
          contents += hypothesisTextField1
          contents += hypothesisTextField2
          contents += hypothesisTextField3
          contents += hypothesisTextField4
          contents += hypothesisTextField5
          contents += hypothesisTextField6
          contents += hypothesisTextField7
          contents += hypothesisTextField8
        }

        val example: FlowPanel = new FlowPanel {
          val exampleIcon = new Label()
          exampleIcon.icon = new ImageIcon("src/main/resources/trump.png")
          val exampleText = new TextArea()
          exampleText.background = new Color(238, 238, 238)
          exampleText.editable = false

          println(Main.exampleString)
          Main.buildExampleAppString()
          exampleText.text = "Ejemplo de app: \n" + Main.exampleString
          val exampleFont: Font = Font("Monaco", Font.Plain, 15)
          exampleText.font = exampleFont
          contents += exampleIcon
          contents += exampleText
        }

        pages += new Page("Inicio", home)
        pages += new Page("Gráficas", graphs)
        pages += new Page("Hipótesis", hypothesis)
        pages += new Page("Ejemplo", example)
      }

      val dimension = new Dimension(580, 700)
      tabs.preferredSize = dimension
      tabs.minimumSize = dimension
      tabs.maximumSize = dimension


      val list: ListView[TabbedPane.Page] = new ListView(tabs.pages) {
        selectIndices(0)
        selection.intervalMode = ListView.IntervalMode.Single
        renderer = ListView.Renderer(_.title)
      }
      val center: SplitPane = new SplitPane(Orientation.Vertical, new ScrollPane(list), tabs) {
        oneTouchExpandable = true
        continuousLayout = true
      }
      layout(center) = Center

      /*
       * This slider is used above, so we need lazy initialization semantics.
       * Objects or lazy vals are the way to go, but objects give us better
       * type inference at times.
       */
      object slider extends Slider {
        resizable = false
        min = 0
        value = tabs.selection.index
        max = tabs.pages.size - 1
        majorTickSpacing = 1
      }

      layout(slider) = South

      /*
       * Establish connection between the tab pane, slider, and list view.
       */
      listenTo(slider)
      listenTo(tabs.selection)
      listenTo(list.selection)
      reactions += {
        case ValueChanged(`slider`) =>
          if (!slider.adjusting || reactLive) tabs.selection.index = slider.value
        case SelectionChanged(`tabs`) =>
          slider.value = tabs.selection.index
          list.selectIndices(tabs.selection.index)
        case SelectionChanged(`list`) =>
          if (list.selection.items.length == 1)
            tabs.selection.page = list.selection.items.head
      }
    }
  }
}
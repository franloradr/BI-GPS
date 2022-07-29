# Google PlayStore Analytics
Business Intelligence - Google Play Store


	   ____                   _        ____  _             ____  _                 
	  / ___| ___   ___   __ _| | ___  |  _ \| | __ _ _   _/ ___|| |_ ___  _ __ ___ 
	 | |  _ / _ \ / _ \ / _` | |/ _ \ | |_) | |/ _` | | | \___ \| __/ _ \| '__/ _ \
	 | |_| | (_) | (_) | (_| | |  __/ |  __/| | (_| | |_| |___) | || (_) | | |  __/
	  \____|\___/ \___/ \__, |_|\___| |_|   |_|\__,_|\__, |____/ \__\___/|_|  \___|
	                    |___/                        |___/                        
 

	Universidad de Sevilla - Escuela Técnica Superior de Ingeniería Informática

				 Inteligencia Empresarial


CSV Data processing containing Google Store apps and interesting information such as date release, category, amount of reviews per app, rating, app size, app price, content rating, genres, etc.

# Authors
 - Daniel Moreno Soto
 - Francisco José Alonso Parejo
 - Francisco Jesús Belmonte Pintre

# Hypothesis statements
 - e.g: Does a high price (> 50 $) imply low sellings (< 1000 units)

$$c1: price,\ c2: units\ sold,\ x: higher\ than\ 50€ ,\ y: lower\ than\ 1000\ units$$

$$\frac{N_(c1:x \bigcap  c2:y) }{N_(c1:x)}$$ 

# Language
 - Scala

# Statistical representations
 - Vegas-Viz

# Files
 - App --> Class model. Each instance will represent a Google Store app
 - FileIO --> RDD Apps generation from CSV File
 - Main --> Data extraction and RDD Hypothesis statements
 - Plot --> Statistical representations
 - GUI --> Graphic User Interface

# Captures
<p align="center">
  <img src="https://raw.githubusercontent.com/franloradr/BI-GPS/master/captures/G-Hipotesis.PNG">
</p><br>
<p align="center">
  <img src="https://raw.githubusercontent.com/franloradr/BI-GPS/master/captures/C-Grafica1.PNG">
</p><br>

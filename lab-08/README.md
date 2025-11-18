
1. CircleSizeSlider

a. What function changes the circle size?
The diameter changes inside the slider’s stateChanged() method, which calls setCircleDiameter() and then repaint().

b. When and how often is the circle redrawn?
Every time the slider value changes, repaint() is called and the circle is redrawn immediately.

2. RandomHexPrinter & RawRandomHexStreamer

a. Where is the random data coming from?
From the random.org API, which provides true random bytes from atmospheric noise.

b. Is the data truly random or pseudo random?
It is truly random, unlike algorithm-based pseudo-random generators.

3. Logistic Map (LogisticMapGenerator)

a. Growth values that lead to extinction:
r < 1

b. Growth values that converge to a constant population:
1 < r < 3

c. Growth values that oscillate or become chaotic:
Oscillations: 3 < r < ~3.57
Chaos: r > 3.57

4. Visualizing (CSVtoPNG, GraphFromCSV, GraphAnimationFromCSV)

The graphs clearly show:

smooth decay or stabilization for r < 3

two, four, or eight-cycle oscillations for mid-range r

chaotic patterns for r near 4

Small code changes : adjusting colors or line sizes.

5. RabbitPopulationGraph

a. What events trigger redraw?
Clicking Draw, and releasing the iteration slider.

b. Did any conclusions change?
No major changes; the GUI confirms:

low r stabilizes

medium r oscillates

high r becomes chaotic

6. RabbitAgentSimulationEmergent

a. What determines the rabbit population limit?
The fixed carrot supply per generation is the limiting factor.

b. How is the carrot population maintained?
It resets every generation because a new CarrotSupply object is created.

c. How close is this simulation to logistic map predictions?
The general behavior is similar (limits, oscillations, chaos), but not identical due to randomness and agent interactions.

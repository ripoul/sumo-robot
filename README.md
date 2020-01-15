<h1 align="center">Welcome to Sumo-Robot 👋</h1>

This project show how to build Sumot Robots. You can see [here](https://www.youtube.com/watch?v=gIYMAymGzdI) what are sumo robots.
Usually those robots have an IA for the fighting parts but not ours. We created a simple remote on smartphone (android) and we connected a bluetooth shield to arduino.

# :wrench: Needed materials

- Arduino uno r3
- some wire ([here](https://www.gotronic.fr/art-pack-de-cables-de-connexion-12411.htm))
- a bluetooth module : [arduino HC-05](https://www.gotronic.fr/art-module-bluetooth-hc05-26097.htm)
- an android smartphone
- some wheel and motors (example for us : [motors](https://www.gotronic.fr/art-paire-de-motoreducteurs-dg01d-18760.htm) and [wheel](https://www.gotronic.fr/art-paire-de-roues-jaunes-eco-dgr002-18762.htm))
- dual H-Bridge motor driver L298N ([L298N](https://www.gotronic.fr/art-commande-de-2-moteurs-sbc-motodriver2-27418.htm))

# :hammer: Installation

## Robots

You can find the electric scheme of the project in the `/docs` folder. 

<p align="center">
  <img src="https://github.com/ripoul/sumo-robot/blob/master/docs/robot_circuit_diagram.png?raw=true" width="500" alt="arduino_circuit_diagram">
</p>

The program for controling the sumo robot is in the `/sumo-robot/sumo-robot.ino` file.
You can use the `prog-bt.ino` program to configure the hc-05 bluetooth module (name and password for example).

## Android

You can find the mobile app in the `/app` folder. It's an android-studio application.

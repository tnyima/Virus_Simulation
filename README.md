# Virus_Simulation

  This is the virus simulation. It is a program meant to simulate how a virus spreads in a population. The program is based of the SIR model, where the main variables affecting the spread of a virus or disease are population density, transmission rate and infectious period of the disease. This is a interactive simulation. 

  When the user runs the program they will be prompted to question in the console. They are asked to enter a the sample population, which is supposed to represent the population density, then the transmission rate between 0 and 1 (0 - 100%), infectious period and the recovery time. It is advised that the user does not enter more than 100 into the sample size, because it will start lagging. As for the infectious period represents the amount of time an infected individual can transmit the disease to others. The transmission rate meant to used to calculate the probability that a person might get infected when they come in contact with someone who is infected. The recovery time is the average amount of time that an individual takes to recover from the disease or virus, in the program it is a constant set by the user. 
  
  The program represents people as circles that come in 3 colors. Black for a susceptible, i.e none infected person who can get infected, red for an infected individual and green for a recovered individual. These people will be randomly moving on screen; they might appear to moving off screen at first, but they eventually turn back as they reach the borders of the screen. Individuals will change their colors as they get infected and as they recover. 
  
  The simulation will also create a graph with the data inputted by the user that shows the rate of the infected and recovered people. The x-value for the graph is the time and y value will either be recovered or infected. Since there will be two lines showing the infected and recovered people. The time is represented by nanoseconds, that is converted to seconds. On the graph, time will increased to show a live visual of how the simulation works. 

  The user should run multiple instances of the simulation, changing the numbers they input each time to observe how these different variables affect the spread of a disease or virus. 

Credit to Cell Obsorbtion Lab for helping us with the object's movements.
Credit to knowm.org for assisting us with creating the graph for the simulation. 

# Elevator System with UI

## Class Diagram

![Class Diagram](https://i.postimg.cc/k5VzSqGx/elevator-Class-Diagram-drawio.png)

## Image of the ui:
![UI](https://i.postimg.cc/1XF0pRNN/Screenshot-2024-10-27-at-11-38-55-PM.png)


## Description
* The project is an elevator system with a user interface.
* Currently I am using a slot based strategy to select elevator. According to this strategy the elevator will be selected based on which slot it falls into. The total number of slots are equal to total floors divided by the number of elevators.
* I have categorized the requests of two types:
    1. The request which comes from the internal display button. That request will only have a floor on which the elevator needs to go.
    2. The request which comes from the external button. That request will have a floor and a direction in which the elevator needs to go after reaching on that floor.
* The strategy for processing the requests for a particular elevator is present in the `ElevatorController` class. I have used Scan and Look for this purpose.
* According to this strategy, the elevator will first move in the up direction till there are no more requests in the `upQueue`. Also when a request comes, if the elevator is moving up and the request is to go up and the floor is above the current floor, then the request will be added to the `upQueue`. If the request is to go down and the floor is below the current floor, then the request will be added to the `downQueue`.




# Graduate Admission System
This is a project to recreate a simple version of the Carleton graduate studies admission system, https://360.carleton.ca, for the course SYSC 4806.

[This link takes you to the production site](https://graduate-admission-system.herokuapp.com/), which will be stable but not always up to date

[This link takes you to the staging site](https://graduate-admission-staging.herokuapp.com/), which is more up to date, but may occasionally be down. This site will be merged into the production site every couple days.

Current staging status: [![Build Status](https://travis-ci.org/CameronRushton/Graduate-Admission-System.svg?branch=develop)](https://travis-ci.org/CameronRushton/Graduate-Admission-System)

## Authors
Eric Bedard <br>
Madelyn Krasnay <br>
Luke Newton <br>
Cameron Rushton <br>
Kevin Sun 

## Project Development Information
The project is broken up into weekly sprints, each with their own Kanban board. These can be viewed on the repository's "Projects" tab, here: https://github.com/CameronRushton/Graduate-Admission-System/projects

## Project Status
The project is completed for the grading purposes of SYSC4806 as of April 3, 2020. There may be future updates by group members for additional functionality.

## Instructions to Run Locally
To run the application locally, follow the steps on the [getting started page of the project wiki](https://github.com/CameronRushton/Graduate-Admission-System/wiki/Developer-setup)

## Login Details
The system has three different roles for different system views: these are student, prof, and admin
Each role has one userAccount in the database to show that it works. The details for logging in are below:

### student account:
email: GAS.student4806@gmail.com <br/>
password: wB2G4GDz2D

### professor account:
email: GAS.prof4806@gmail.com <br/>
password: 3HwRTrYQsc

### admin account:
email: GAS.staff4806@gmail.com <br/>
password: jU4wAx3r4j

## Model Class Diagram
![UML Class Diagram](/diagrams/ClassDiagram.PNG)

## Database Diagram
![Database Diagram](/diagrams/Database.PNG)

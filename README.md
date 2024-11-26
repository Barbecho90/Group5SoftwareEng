# 🃏 Multiplayer BlackJack

This project outlines the interface design, architectural components, and detailed class structures necessary to implement a multiplayer Blackjack game. The document serves as a comprehensive guide for the team, ensuring clarity on the game’s requirements and design considerations.

## 👥 **Our Team**:

Santiago Ruiz Arias  
Nikolas Alvarado  
Carolina Barbecho  
Matthew Vincent Carreon  
Oscar Avendano  
Brandon Sandoval

## 🚀 Prerequisites

Before getting started, make sure to set up the following:

**1️. Clone the Repository**

Copy the project to your local machine by running:

```bash
git clone https://github.com/Barbecho90/Group5SoftwareEng.git
```

**2️. Add Required Jars**

Inside the `BlackjackGame` there is a folder called `jarDependencies` and contains:

- hamcrest-core-1.3.jar
- junit-4.13.2.jar
- shared.jar

You will need to add `hamcrest-core-1.3.jar` and `junit-4.13.2.jar` to all three of the projects. You
can do this by right clicking the project --> click properties --> click Java Build Path --> then select
the libraries tab. Then you will click on the module path and then click the `Add External JARs...` button
on the right hand side. Then navigate to the `jarDependencies` folder and select the JARs and click open.

For the server and client be sure to add the `shared.jar` in order to build our shared code.

Be sure that your project is able to build before starting to code.

**3. Local Development**

When developing locally you will need to chackout a branch in order to commit your code, to do this in terminal
all you need to do is run

```bash
git checkout -b <your-branch-here>
```

pushing to the main branch is blocked so you will need to open a pr to merge it in.

**4. Updating the Shared Project**

Because we have shared models between the client and server we have a shared project that allows us to
reuse code accross the two projects. So when you update the models you will want to export the shared
project as a JAR and then replace the one in the `jarDependencies` folder so that it gets updated for
everyone else when we pull the code. To do this

1. Right Click on shared

2. Click Export

3. Expand the Java Folder --> click JAR File --> click next

4. Make sure the destination is in `jarDependencies` and called `shared.jar` --> click next

5. click next (make sure nothing is selected)

6. Click browse next to main class --> click Main class --> click OK

7. Click finish

8. Click Yes when asked to Overwrite

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

You will need to add `hamcrest-core-1.3.jar` and `junit-4.13.2.jar` to all three of the projects. 
You can do this by right clicking the project --> click properties --> click Java Build Path --> then select
the libraries tab. 

Then you will click on the module path and then click the `Add External JARs...` button
on the right hand side. Then navigate to the `jarDependencies` folder and select the JARs and click open.

For the server and client be sure to add the `shared.jar` in order to build our shared code.

This is what the server project looks like:
![added-jars](https://github.com/user-attachments/assets/ce291d97-c607-4920-b304-8267d2510213)

Be sure that your project is able to build before starting to code.

For the JUnit testing, ensure that share/test package is added to the Java Build Path. You can do this by right clicking the project --> click properties --> click Java Build Path --> select the source tab --> click Add Folder --> select the shared/test folder.

*** 
**3. Local Development**

When developing locally you will need to chackout a branch in order to commit your code, to do this in terminal
all you need to do is run

```bash
git checkout -b <your-branch-here>
```

pushing to the main branch is blocked so you will need to open a pr to merge it in. You can also use 
[GitHub Desktop](https://docs.github.com/en/desktop/installing-and-authenticating-to-github-desktop/installing-github-desktop) 
if you want a GUI.

**4. Updating the Shared Project**

Because we have shared models between the client and server we have a shared project that allows us to
reuse code accross the two projects. So when you update the models you will want to export the shared
project as a JAR and then replace the one in the `jarDependencies` folder so that it gets updated for
everyone else when we pull the code. To do this

1. Right Click on shared

2. Click Export

3. Expand the Java Folder --> click JAR File --> click next

![Select Jar File](https://github.com/user-attachments/assets/c4d60ab8-36dc-46ab-ab66-28ca9ae5b4dd)

4. Make sure the destination is in `jarDependencies` and called `shared.jar` --> click next

![jar-spec](https://github.com/user-attachments/assets/09362af8-342a-4d3c-9bc2-dcd61bfaf774)

5. click next (make sure nothing is selected)

![packing options](https://github.com/user-attachments/assets/187fe737-f410-47bc-b057-ad873d2ee0cd)

6. Click browse next to main class --> click Main class --> click OK

![jar-manifest](https://github.com/user-attachments/assets/9aa0c800-4d60-4272-af82-8011a3e29095)

![select-main](https://github.com/user-attachments/assets/4651fba2-48b6-4649-907b-741fb2aae537)

![jar-manifest-2](https://github.com/user-attachments/assets/02912aac-48eb-451d-ae82-c4b9595e6fce)

7. Click finish

8. Click Yes when asked to Overwrite

![overwrite](https://github.com/user-attachments/assets/16e2e86e-3f5b-4db9-a27d-5ad326ad74fc)

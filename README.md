# HealthyGymAPI

### Architecture:

- #### UI Presentation Layer

  - ##### HealthyGym iOS Mobile Application

    - The mobile application serves as the user interface to the gym members.  The app gives the user the ability to input their user information, check-in for workouts, and report their infection status.  This information is persisted through the HealthyGymAPI.  The app also leverages information derived from the Data Intelligence Layer to inform the user of the risk status of checking in for a workout on a selected date/time and offering insight into alternative times.  
    - The application is written in Xcode/Swift for iOS

- #### Service Layer

  - ##### HealthyGymAPI

    - The api serves as the service layer for the HealthyGym application.  The api provides and interface to the models for User, CheckIn, Report.  It also services the risk model data that is displayed on the user interface.  The api leverages the entity framework and hibernate repository structure for data persistence.  This api offers basic CRUD operations such as GET, PUT, POST for interactivity.  The Service Layer is the bridge between the UI Layer and the Data Layer where the data is persisted.   
    - The api is written in Java 8 on Spring Boot 2.0
    - The application is containerized using Docker
    - The docker image runs on an AWS EC2 Amazon Linux instance

- #### Data Layer

  - ##### Relational Database in MySQL

    - The HealthyGym Database resides in a MySQL database and serves as the Data Layer for the HealthyGym application and api.  The Data Intelligence Layer also leverages the HealthyGym database data to run Risk Modeling analysis resulting in additional data persisted.
    - The MySQL database runs on AWS RDS as a free tier relational database

- #### Data Intelligence Layer

  - ##### Support Vector Machine Risk Model

    - The Data Intelligence Layer is responsible for analyzing data from the Healthy Gym application and running Risk Modeling.  A Support Vector Machine is used to run prediction analysis. This data is then used to help the users of HealthyGym to make informed decisions about workout times.
    - The SVM is built using Python and runs as a Lambda function in AWS.  The result data is stored in the Data Layer so it is accessible via the Service Layer 


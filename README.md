CS-537-CSM
==========

Cal State Mobile - mobile website for the Cal State university students.

## How to run the project
### Use Eclipse
1. Under eclipse, go to `file>import` select `Existing Maven Project`
2. Select the `CS-537-CSM` folder for the **Root Directory**, and click finish
3. To run the project, right click on the project `run as` and select `run on server` select `tomcat 7` as the server

### Use Terminal
1. Download [maven](http://maven.apache.org/) if you are using windows.
2. Execute `mvn tomcat:run` at the root directory of this project (CS-537-CSM)
3. Open browser and go to `http://localhost:8080/csm` to check if tomcat is running

## Project architecture
```
.
├── pom.xml
├── README.md
└── src
    ├── main													--> where we put source codes
    │   ├── java												--> Java source codes
    │   │   └── org
    │   │       └── csm
    │   │           ├── controllers								--> controllers
    │   │           ├── models									--> POJO models
    │   │           │   └── dao									--> custom dao
    │   │           └── providers								--> Magic
    │   ├── resources
    │   ├── scripts												--> SQL scripts
    │   └── webapp												--> front end stuff
    │       ├── index.html
    │       └── WEB-INF
    │           └── web.xml
    └── test													--> where we put unit tests
        ├── java
        └── resources

```

CS-537-CSM
==========

Cal State Mobile - mobile website for the Cal State university students.

## :information_desk_person: Developer Guidelines
* `master` branch should always be stable and deployable
* When you are working any feature, please branch out from latest commit on `master` branch, create a **pull-request** when you are done with implementation
	* Always do a `git fetch && git pull origin master` when you start branch from master
* Before you start coding, write down the `test cases` for the feature, and implement test cases under `test/java` so that you can test your codes when you are done. This will help us to create more reliable code
* When you open a pull request, be descriptive on what you have done. Assign a person to review your code before you merge your code to the master
* Document your code! Keep your code maintable, you are not the only one to look at it.

## :question: How to run the project
### :waxing_gibbous_moon: Use Eclipse
1. Under [Eclipse](http://eclipse.org), go to `file>import` select `Existing Maven Project`
2. Select the `CS-537-CSM` folder for the **Root Directory**, and click finish
3. To run the project, right click on the project `run as` and select `run on server` select `tomcat 7` as the server

### :+1: Use Terminal
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

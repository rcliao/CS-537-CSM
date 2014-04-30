:iphone: CS-537-CSM
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
3. To run the project, right click on the project `run as` and select `maven build` 
4. In the dialog, type in `tomcat:run` in the goal
5. Open any browser of your choice, type in the following in the address `http://localhost:8080/csm/rest/hello/user`

### :+1: Use Terminal
1. Download [maven](http://maven.apache.org/) if you are using windows.
2. Execute `mvn tomcat:run` at the root directory of this project (CS-537-CSM)
3. Open browser and go to `http://localhost:8080/csm` to check if tomcat is running

## :house: Project Architecture
```
.
├── pom.xml
├── README.md
└── src								// where our source code goes
   ├── main
   │   ├── java							// Java source codes
   │   ├── resources
   │   └── webapp						// front end stuff
   │       ├── bower_components					// bower componetns / front end libraries
   │       ├── css						// css files
   │       ├── img						// images
   │       ├── index.html					// home page
   │       ├── js						// angularjs logic
   │       ├── lib						// extra libraries
   │       ├── partials						// partial pages
   │       ├── test						// karma test
   │       └── WEB-INF
   ├── scripts							// sql scripts
   │   ├── create-csm.sql
   │   └── drop-csm.sql
   └── test							// JUnit Test
       ├── java
       │   └── org
       └── resources

```

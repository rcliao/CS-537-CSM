CS-537-CSM
==========

Cal State Mobile - mobile website for the Cal State university students.

## How to run the project
### Use Eclipse
1. Under eclipse, go to `file>create new project` select `dynamic web project`
2. Uncheck the option `use default location` 
3. Select the `CS-537-CSM` folder, and you should have the project imported
4. To run the project, right click on the project `run as` and select `run on server` select `tomcat 7` as the server 

## Project architecture
```
.
├── README.md                           | readme file
├── src                                 | where we store our java source codes
│   └── cs537
│       └── csm
│           └── controller              | controllers
└── WebContent                          | front end stuff
    ├── index.html                      | home page
    ├── META-INF                        | may remove it later
    └── WEB-INF                         | web-information
```

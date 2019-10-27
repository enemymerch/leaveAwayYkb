# leaveAwayYkb

- create package with **mvn -Dmaven.test.skip=true package** because testcode is not complete, otherwise you will get an error. 
- run with **java -jar unitcase-0.0.1-SNAPSHOT.jar** (application will start on port 8080. if you want to change the port number to something else change the **--server.port** property at **application.properties** file.)
-  PS : if you want to change the h2 db directory, you need to change the **spring.datasource.url** property too, for now db is on memory, if you want to persist the data, change in memory db to file. (example : **spring.datasource.url=jdbc:h2:file:~/workspace/h2**)

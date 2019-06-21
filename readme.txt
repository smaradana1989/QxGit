Before launching the Bootstrap action you need to:
	* Open the bootstrap.properties and fill all the properties. For apim key and quote end point ask your administrator for more information
	* Setup your quote model in the ModelQuote.java
	
To bootstrap you can launch the action :
		- In eclipse by right click on the src/main/java/com.pros/quotex/application/bootstrap/model/Bootstrap.java file select Run As-> Java application.
		- With command line by typing:
			Linux => ./gradlew bootstrap 
			Windows => gradlew.bat bootstrap
	In the console view for eclipse (or command line) you will have the output with all properties used for the call and the result or errors

Before launching the UpdateModelMatrix action you need to:
    * Open the bootstrap.properties and fill all the properties. For apim key and quote end point ask your administrator for more information
    * Open the updateModelMatrix.properties and fill all the properties.

To update the model matrix you can launch the action :
		- In eclipse by right click on the src/main/java/com.pros/quotex/application/bootstrap/model/UpdateModelMatrix.java file select Run As-> Java application.
		- With command line by typing:
			Linux => ./gradlew updateModelMatrix
			Windows => gradlew.bat updateModelMatrix
	In the console view for eclipse (or command line) you will have the output with all properties used for the call and the result or errors
			
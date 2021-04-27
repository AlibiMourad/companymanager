# REST API Assignment with Spring Boot and H2
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 
### Prerequisites

What things you need to install the software and how to install them
Java 1.8+
gradle-7.0
Rest API Client (example: Google Chrome Browser extention Postman) or curl utility installed


```
Download JDK from Oracle website http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
```

### Installing

Follow the instructions to download source code, compile and run

The packaged jar file is self executable jar file, with all dependencies

```
git clone 
gradlew bootRun
The Tomcat webserver runs on port 8080.
```

### Running the JUnite tests
```
gradlew test
```

## Explore Rest APIs

The app defines following CRUD APIs.

    GET api/partners?from={from}&size={size}
    
    POST /api/partners
    
    GET /api/partners/{partnerId}
    
    PUT /api/partners/{partnerId}
    
    DELETE /api/partners/{partnerId}


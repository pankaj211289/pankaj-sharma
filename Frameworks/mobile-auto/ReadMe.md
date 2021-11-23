## Pre Requisite:
```
Framework expects following thinigs before execution:
 1. Emulator device is correctly configured
 2. Appium Server should be installed and up-running while executing tests
 3. Java should be installed
 4. Maven should be installed
```

## Executing Tests
```
 1. Open up Emulator device
 2. Start Appium server with following configurations:
    -  Host : 127.0.0.1
    -  Port : 4723
    -  Click Start Server button
 3. Navigate to project's root directory and execute following command inside terminal:
    -  mvn test -Dsurefire.suiteXmlFiles=smoke-testng.xml
```




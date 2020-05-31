##build project
`./gradlew clean build`

## Run project
`./gradlew bootRun`

## Deploy
`./gradlew clean bootJar`

jar file will be under build/libs/userprojectservicebackend-*.jar

`cd build/libs`

This command can run the server

`java -jar userprojectservicebackend-0.0.1-SNAPSHOT.jar`

If the previous command works, upload this jar file to AWS Elastic Beanstalk

# superchat

Project download, import and run 
1- Download project on github

2- Open the project with intellij idea

3- Import pom.xml process
    Click project structure(File > Project Structure)
    Choose Sdk version in Project (should be upper java 11)
    import pom.xml in Modules. Modules --> Add --> Import Module --> path of project's pom.xml.
    Choose Sdk version again in SDKs.
    
4- Maven install
5- Run /startup/application.java class.

6- If project is running although java classes have error(for example; getUUid()), you should download "Lombok".
File > Settings > Plugins > Search "Lombok" and install. 



# Start Postgres & Pgadmin

```
cd postgresql
docker-compose up -d
```


- Postgresql should be running and bound to port 5431 on localhost


```
cd postgresql
docker-compose down
```

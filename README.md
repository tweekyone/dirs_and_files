# Dirs and Files application

## Guidelines
1. Clone this repository

2. Create database "dirs" (PostgreSQL)

3. Go to project directory

4. Run with console:
```
$ ./gradlew build
$ java -jar build/libs/dirs_and_files-${version} -e DATASOURCE_HOST=${datasource_host} 
DATASOURCE_USER=${datasource_user} DATASOURCE_PASS=${datasource_password}
```

5. Go to http://localhost:8080/
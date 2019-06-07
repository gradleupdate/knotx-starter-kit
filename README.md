# Knot.x Starter Kit

## How can I build my Docker image?
```
./gradlew build
```

## How can I run my Docker image?
```
docker run -p9092:9092 knotx/knotx-starter-kit
```

## How can I verify my Docker image?
The project contains functional tests defined in the `functional` folder. Those tests run your Docker
container and execute functional tests. 
```
./gradlew runTest
```
Please note that functional tests uses port `9092`.
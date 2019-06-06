# Knot.x Starter Kit

## How can I build my Docker image?
```
./gradlew buildImage
```

All next builds should be preceded by a command:
```
./gradlew removeImage
```

## How can I run my Docker image?
```
docker run -p9092:9092 knotx/knotx-starter-kit knotx run-knotx
```

## How can I verify my Knot.x container?
```
curl http://localhost:9092/api/example
```
In the response you should see `Hello from Knot.x!`.
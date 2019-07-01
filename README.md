# Knot.x Starter Kit
Knot.x Starter Kit is a template project that simplifies Knot.x project setup. It allows you to 
customize the [Knot.x distribution](https://github.com/Knotx/knotx-stack) with your own modules and
configuration entries. Then it builds and validates your custom Docker image. 


## How to build
Simply clone the repository and remove `.git` folder
```
$> git clone --branch 2.0.0-RC4 --depth=1 https://github.com/Knotx/knotx-starter-kit.git my-knotx-starter-kit && rm -rf !$/.git
$> cd my-knotx-starter-kit
```
and execute
```

$> ./gradlew build
```
to:
- build all your custom Knot.x modules
- prepare your custom Docker image with all required dependencies (including your custom modules and 
its transitive dependencies)
- validate your Docker image with system tests
`
## How to run
Start Docker container
```
docker run -p8092:8092 knotx/knotx-starter-kit
```
and validate urls:
- [localhost:8092/api/v1/example](http://localhost:8092/api/v1/example)
- [localhost:8092/api/v2/example](http://localhost:8092/api/v2/example)

## What does it contain

### Custom modules

- [API Handler](https://github.com/Knotx/knotx-starter-kit/tree/master/modules/example-api) 
that contains example implementation of a [Handler](https://github.com/Knotx/knotx-server-http/tree/master/api#routing-handlers) 
- [Healthcheck](https://github.com/Knotx/knotx-starter-kit/tree/master/modules/health-check)
that holds example implementation of a [Vert.x Healthcheck](https://vertx.io/docs/vertx-health-check/java/)
- [Action](https://github.com/Knotx/knotx-starter-kit/tree/master/modules/example-action) that
contains example implementation of a [Knot.x Action](https://github.com/Knotx/knotx-fragments/tree/master/handler/api#action)


All modules are built into JAR files and copied to the `KNOTX_HOME/lib` folder in the project Docker image. 
Also all transitive dependencies are automatically downloaded and added to the Docker image.

### Configuration

You can also override the [default Knot.x configuration](https://github.com/Knotx/knotx-stack/tree/master/src/main/packaging/conf)
with your custom settings. All files from the `/conf` directory are copied to `KNOTX_HOME/conf`. So you
can easily modify the Knot.x configuration, reconfigure the logger or update an [Open API](https://github.com/OAI/OpenAPI-Specification) specification.

If you want to add a dependency that is not connected with any custom module such as 
[Knot.x Dashboard](https://github.com/Knotx/knotx-dashboard) you can add this entry in 
`/build.gradle.kts`:

```
dependencies {
    subprojects.forEach { "dist"(project(":${it.name}")) }
    "dist"("io.knotx:knotx-dashboard:${project.version}")
}
```

### Dockerfile

[Dockerfile](https://github.com/Knotx/knotx-starter-kit/blob/master/docker/Dockerfile) is defined 
in the `docker` folder and extends the [Base Knot.x Docker image](https://hub.docker.com/r/knotx/knotx).

##  How to work with SNAPSHOT

You need to build SNAPSHOT Docker image locally. The easiest approach is to use [Knot.x Aggregator](https://github.com/Knotx/knotx-aggregator)
to clone and build all Knot.x modules. Then you need to build base [Knot.x Docker](https://github.com/Knotx/knotx-docker) image.

Now you can update `gradle.properties` with SNAPSHOT version.

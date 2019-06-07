# Knot.x Starter Kit
Knot.x Starter Kit is a template project that you can use when creating some Knot.x extensions.  It 
allows you to easily integrate all custom modules with Knot.x Stack and builds a custom Docker image.

## What does it contain?
It contains two custom modules:
- custom [Action](https://github.com/Knotx/knotx-fragments-handler/tree/master/api) implementation in `modules/custom-action`
- custom [Routing Handler](https://github.com/Knotx/knotx-server-http/tree/master/api) implementation in `modules/custom-handler`

Both modules are built to JAR files and copied to `KNOTX_HOME/lib` folder in Docker image. Also 
all transitive dependencies are automatically downloaded and added to Docker image.

You can also override the [default Knot.x configuration](https://github.com/Knotx/knotx-stack/tree/master/src/main/packaging/conf)
with your custom settings. All files from `/conf` directory are copied to `KNOTX_HOME/conf`. So you
can easily modify Knot.x configuration, reconfigure logger or update [Open API](https://github.com/OAI/OpenAPI-Specification) specification.

If you need to add a dependency that is not connected with any custom module like 
[Knot.x Dashboard](https://github.com/Knotx/knotx-dashboard) you can add this entry in 
`/build.gradle.kts`:

```
dependencies {
    subprojects.forEach { "dist"(project(":${it.name}")) }
    "dist"("io.knotx:knotx-dashboard")
}
```

[Dockerfile](https://github.com/Knotx/knotx-starter-kit/blob/master/docker/Dockerfile) is defined 
in `docker` folder and extends the [Base Knot.x Docker image](https://hub.docker.com/r/knotx/knotx) 
that is created [here](https://github.com/Knotx/knotx-docker).

Please note that we use 2.0.0-SNAPSHOT image version until we release Knot.x 2.0. We do not deploy
SNAPSHOT images to Docker Hub so you need to build it by your own, see [Knot.x Docker](https://github.com/Knotx/knotx-docker).

## How to build Docker image?
Simply run the command
```
./gradlew build
```
that copies
- custom modules with transitive dependencies
- configuration files
- Dockerfile

to `build` directory and build Docker image based on Dockerfile. Then it starts container with the 
new image and validate functional tests.

## How can I run my Docker image?
```
docker run -p9092:9092 knotx/knotx-starter-kit
```
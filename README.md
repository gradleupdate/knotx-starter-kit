# Knot.x Starter Kit
Knot.x Starter Kit is a template project that simplifies Knot.x project setup. It allows you to customize
[Knot.x Stack](https://github.com/Knotx/knotx-stack) and deliver it as a Docker image.

## How to start
To start a new Knot.x project with extensions simply [download this repository as a ZIP](https://github.com/Knotx/knotx-starter-kit/archive/master.zip) and unzip it.

You may also use the following command:
```
git clone --depth=1 git@github.com:Knotx/knotx-starter-kit.git knotx-example && rm -rf !$/.git
```
to clone the repository contents to the `knotx-example` directory and start without git history.

## How to build Docker image?
Simply run the command
```
./gradlew build
```
that copies
- custom modules with transitive dependencies
- configuration files
- Dockerfile

to `build` directory and build Docker image based on Dockerfile. 

Then it starts container with the new image and validate functional tests.

## How can I run my Docker image?
```
docker run -p8092:8092 knotx/knotx-starter-kit
```

## What does it contain?

- [API Handler](https://github.com/Knotx/knotx-starter-kit/tree/master/modules/example-api) 
that contains example implementation of a [**Handler**](https://github.com/Knotx/knotx-server-http/tree/master/api#routing-handlers) 
- [Healthcheck](https://github.com/Knotx/knotx-starter-kit/tree/master/modules/health-check)
that holds example implementation of a [Vert.x Healthcheck](https://vertx.io/docs/vertx-health-check/java/)
- [Action](https://github.com/Knotx/knotx-starter-kit/tree/master/modules/example-action) that
contains example implementation of a [Knot.x Action](https://github.com/Knotx/knotx-fragments/tree/master/handler/api#action)


All modules are built into JAR files and copied to the `KNOTX_HOME/lib` folder in the project Docker image. 
Also all transitive dependencies are automatically downloaded and added to the Docker image.

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

[Dockerfile](https://github.com/Knotx/knotx-starter-kit/blob/master/docker/Dockerfile) is defined 
in the `docker` folder and extends the [Base Knot.x Docker image](https://hub.docker.com/r/knotx/knotx).

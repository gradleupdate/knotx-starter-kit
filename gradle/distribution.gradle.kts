/*
 * Copyright (C) 2019 Knot.x Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
tasks.register<Copy>("copyModulesWithDeps") {
    group = "distribution"

    from(configurations.named("dist"))
    into("$buildDir/lib")

    mustRunAfter("cleanDistribution")
}

tasks.register<Copy>("copyConfigs") {
    group = "distribution"

    from("conf")
    into("$buildDir/conf")

    mustRunAfter("cleanDistribution")
}

tasks.register<Copy>("copyDockerfile") {
    group = "distribution"

    from("docker")
    into("$buildDir")

    mustRunAfter("cleanDistribution")
}

tasks.register<Delete>("cleanDistribution") {
    group = "build"

    delete(fileTree("$buildDir").matching {
        exclude(".docker")
    })
}

tasks.register("prepareDocker") {
    dependsOn("cleanDistribution", "copyModulesWithDeps", "copyConfigs", "copyDockerfile")
}
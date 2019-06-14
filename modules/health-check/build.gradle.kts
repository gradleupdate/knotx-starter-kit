val knotxVersion = "2.0.0-SNAPSHOT"

plugins {
    `java-library`
}

dependencies {
    implementation(platform("io.knotx:knotx-dependencies:$knotxVersion"))
    "io.knotx:knotx".let { v ->
        implementation("$v-fragments-handler-api:$knotxVersion")
    }
    "io.vertx:vertx".let { v ->
        implementation("$v-core")
        implementation("$v-rx-java2")
        implementation("$v-health-check")
    }

}

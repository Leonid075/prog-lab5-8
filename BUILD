load("@rules_java//java:defs.bzl", "java_binary")

package(default_visibility = ["//visibility:public"])

java_binary(
    name = "main",
    main_class = "Main",
    srcs = glob(["src/main/java/Main.java"]),
    deps = [":labwork", ":jackson", ":cm"],
)

java_library(
    name = "labwork",
    srcs = glob(["src/main/java/ru/p3xi/labwork/*.java"]),
)

java_library(
    name = "cm",
    srcs = glob(["src/main/java/ru/p3xi/cm/*.java"]),
    deps = [":labwork"],
)

java_import(
    name = "jackson",
    jars = ["src/main/java/jackson-databind-2.16.1.jar",],
    deps = [":jackson-core", ":jackson-annotations", ":jackson-datatype"],
)

java_import(
    name = "jackson-core",
    jars = ["src/main/java/jackson-core-2.16.1.jar",],
)

java_import(
    name = "jackson-annotations",
    jars = ["src/main/java/jackson-annotations-2.16.1.jar",],
)

java_import(
    name = "jackson-datatype",
    jars = ["src/main/java/jackson-datatype-jsr310-2.16.1.jar",],
)

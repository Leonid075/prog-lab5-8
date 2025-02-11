load("@rules_java//java:defs.bzl", "java_binary")

package(default_visibility = ["//visibility:public"])

java_binary(
    name = "main",
    main_class = "Main",
    srcs = glob(["src/main/java/Main.java"]),
    deploy_manifest_lines = ["Main-Class: Main"],
    deps = [":labwork"],
)

java_library(
    name = "labwork",
    srcs = glob(["src/main/java/ru/p3xi/labwork/*.java"]),
)

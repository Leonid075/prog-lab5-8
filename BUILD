load("@rules_java//java:defs.bzl", "java_binary")

package(default_visibility = ["//visibility:public"])

# client
java_binary(
    name = "client",
    main_class = "Main",
    srcs = glob(["src/client/java/Main.java"]),
    deps = [":labwork", ":cm", ":client-commands", ":terminal", ":console", ":cnet", ":request"],
)

java_library(
    name = "cnet",
    srcs = glob(["src/client/java/ru/p3xi/cnet/*.java"]),
    deps = [":request"],
)

java_library(
    name = "terminal",
    srcs = glob(["src/client/java/ru/p3xi/terminal/*.java"]),
    deps = [":labwork", ":cm", ":client-commands", ":console", ":cnet", ":request"],
)

java_library(
    name = "client-commands",
    srcs = glob(["src/client/java/ru/p3xi/ccommands/*.java"]),
    deps = [":labwork", ":cm", ":jackson", ":jackson-dataformat", ":console", ":file", ":request", "cnet"],
)

# server
java_binary(
    name = "server",
    main_class = "Main",
    srcs = glob(["src/server/java/Main.java", "src/server/java/CommandProcessor.java"]),
    deps = [":labwork", ":cm", ":server-commands", ":terminal", ":console", ":snet", ":request"],
)

java_library(
    name = "snet",
    srcs = glob(["src/server/java/ru/p3xi/snet/*.java"]),
    deps = [":request"],
)

java_library(
    name = "cm",
    srcs = glob(["src/server/java/ru/p3xi/cm/*.java"]),
    deps = [":labwork", ":jackson", ":jackson-dataformat", ":jackson-annotations", ":file",],
)

java_library(
    name = "server-commands",
    srcs = glob(["src/server/java/ru/p3xi/scommands/*.java"]),
    deps = [":labwork", ":cm", ":jackson", ":jackson-dataformat", ":file", ":request", "snet"],
)

# common
java_library(
    name = "request",
    srcs = glob(["src/common/java/ru/p3xi/request/*.java"]),
    deps = [":labwork"],
)

java_library(
    name = "labwork",
    srcs = glob(["src/common/java/ru/p3xi/labwork/*.java"]),
    deps = [":jackson", ":jackson-annotations", ":console",],
)

java_library(
    name = "console",
    srcs = glob(["src/common/java/ru/p3xi/console/*.java"]),
    deps = [":file",],
)

java_library(
    name = "file",
    srcs = glob(["src/common/java/ru/p3xi/file/*.java"]),
    deps = [],
)

java_import(
    name = "jackson",
    jars = ["src/common/java/jackson-databind-2.16.1.jar",],
    deps = [":jackson-core", ":jackson-annotations", ":jackson-datatype", "jackson-dataformat"],
)

java_import(
    name = "jackson-core",
    jars = ["src/common/java/jackson-core-2.16.1.jar",],
)

java_import(
    name = "jackson-annotations",
    jars = ["src/common/java/jackson-annotations-2.16.1.jar",],
)

java_import(
    name = "jackson-datatype",
    jars = ["src/common/java/jackson-datatype-jsr310-2.16.1.jar",],
)

java_import(
    name = "stax2-api",
    jars = ["src/common/java/stax2-api-4.0.0.jar",],
)

java_import(
    name = "jackson-dataformat",
    jars = ["src/common/java/jackson-dataformat-xml-2.16.1.jar",],
    deps = [":jackson-annotations", ":jackson-datatype", "jackson-core", "stax2-api"],
)

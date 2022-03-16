cc_library(
    name = "substring",
    srcs = [
        "substring.cc",
    ],
    hdrs = ["substring.h"],
)

cc_test(
  name = "substring_test",
  size = "small",
  srcs = ["substring_test.cc"],
  deps = ["@com_google_googletest//:gtest_main", ":substring"],
)

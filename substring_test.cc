#include <gtest/gtest.h>
#include "substring.h"

TEST(SubstringTest, BasicAssertions) {
    EXPECT_EQ(wob::find("a", "a"), 0);
    EXPECT_EQ(wob::find("a", "ba"), 1);
    EXPECT_EQ(wob::find("abcd", "abcabcdabcde"), 3);
    EXPECT_EQ(wob::find("a", "b"), -1);
}

TEST(SubstringTest, EmptyStrings) {
    EXPECT_EQ(wob::find("", "a"), 0);
    EXPECT_EQ(wob::find("a", ""), -1);
}

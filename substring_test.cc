#include <gtest/gtest.h>
#include "substring.h"

TEST(SubstringTest, BasicAssertions) {
    EXPECT_EQ(wob::find("a", "a"), std::make_tuple(true, 0));
    EXPECT_EQ(wob::find("a", "ba"), std::make_tuple(true, 1));
    EXPECT_EQ(wob::find("abcd", "abcabcdabcde"), std::make_tuple(true, 3));
    EXPECT_EQ(std::get<0>(wob::find("a", "b")), false);
}

TEST(SubstringTest, EmptyStrings) {
    EXPECT_EQ(wob::find("", "a"), std::make_tuple(true, 0));
    EXPECT_EQ(std::get<0>(wob::find("a", "")), false);
}

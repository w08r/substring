#include <gtest/gtest.h>
#include "substring.h"

TEST(SubstringTest, BasicAssertions) {
    EXPECT_EQ(wob::find("a", "a"), std::make_pair(0ul, true));
    EXPECT_EQ(wob::find("a", "ba"), std::make_pair(1ul, true));
    EXPECT_EQ(wob::find("abcd", "abcabcdabcde"), std::make_pair(3ul, true));
    EXPECT_EQ(std::get<1>(wob::find("a", "b")), false);
}

TEST(SubstringTest, EmptyStrings) {
    EXPECT_EQ(wob::find("", "a"), std::make_pair(0ul, true));
    EXPECT_EQ(std::get<1>(wob::find("a", "")), false);
}

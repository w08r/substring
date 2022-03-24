#include "substring.h"
#include <iostream>

namespace wob {
    std::pair<size_t, bool> find(std::string_view needle, std::string_view haystack) {
        auto nl = needle.length();
        for (auto i = 0ul; i < 1 + haystack.length() - nl; i++) {
            auto found = true;
            for (auto j = 0ul; j < nl; j++)
                found &= haystack[i+j] == needle[j];
            if (found) return std::make_pair(i, true);
        }
        return std::make_pair(0ul, false);
    }
}

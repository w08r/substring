#include "substring.h"

namespace wob {
    int find(std::string needle, std::string haystack) {
        auto nl = needle.length();
        for (auto i = 0; i < 1 + haystack.length() - nl; i++) {
            auto found = true;
            for (auto j = 0; j < nl; j++)
                found &= haystack[i+j] == needle[j];
            if (found) return i;
        }
        return -1;
    }
}

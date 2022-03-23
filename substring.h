#include <string>
#include <utility>

namespace wob {
    std::pair<size_t, bool> find(std::string_view needle, std::string_view haystack);
}

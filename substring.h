#include <string>
#include <tuple>

namespace wob {
    std::tuple<bool, size_t> find(std::string_view needle, std::string_view haystack);
}

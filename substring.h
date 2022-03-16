#include <string>
#include <tuple>

namespace wob {
    std::tuple<bool, size_t> find(std::string const& needle, std::string const& haystack);
}

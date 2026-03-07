package {{ cookiecutter.pkg_name }}.domain.keyvalue

interface KeyValueStorage {
    var owner: String?
}

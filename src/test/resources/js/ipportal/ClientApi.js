var ClientApi = function () {};

ClientApi.prototype.getClientByCode = function (name) {
    return 'client code is ' + name;
};

module.exports = new ClientApi();



const API = {};

API.GET = function (options) {
    if (options.id != null) {
        options.url = options.url + "/" + options.id;
    }
    return new Promise((Resolve, Reject) => {
        $.ajax({
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            url: options.url
        }).done(function (data) {
            Resolve(data);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            Reject(textStatus + ': ' + errorThrown);
        });
    })
}


API.POST = function (options) {
    if (options.data == null) {
        return;
    }
    return new Promise((Resolve, Reject) => {
        $.ajax({
            method: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            url: options.url,
            data: options.data
        }).done(function (data) {
            var rs = {...data.rsmap};
            rs.id  = options.IDAPI
            options.callback(rs)
        }).fail(function (jqXHR, textStatus, errorThrown) {
            Reject(textStatus + ': ' + errorThrown);
        });
    })
}


API.SEARCH = function (options) {
    if (options.data == null) {
        return;
    }
    return new Promise((Resolve, Reject) => {
        $.ajax({
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            url: options.url,
            data: options.data
        }).done(function (data) {
            Resolve(data);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            Reject(textStatus + ': ' + errorThrown);
        });
    })
}


API.DELETE = function (options) {
    if (options.id != null) {
        options.url = options.url + "/" + options.id;
    }
    return new Promise((Resolve, Reject) => {
        $.ajax({
            method: 'DELETE',
            dataType: 'json',
            contentType: 'application/json',
            url: options.url,
        }).done(function (data) {
            Resolve(data);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            Reject(textStatus + ': ' + errorThrown);
        });
    })
}


API.PUT = function (options) {
    if (options.data.id == null) {
        return;
    }
    return new Promise((Resolve, Reject) => {
        $.ajax({
            method: 'POST',
            dataType: 'json',
            url: options.url,
            contentType: 'application/json',
            data: options.data
        }).done(function (data) {
            Resolve(data);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            Reject(textStatus + ': ' + errorThrown);
        });
    })
}

export { API }


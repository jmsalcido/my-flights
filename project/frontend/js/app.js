App = Ember.Application.create({
    isEmpty: function isEmpty(str) {
        return (!str || 0 === str.length);
    }
});

App.Router.map(function() {
    // if adding routes, add them here.
});
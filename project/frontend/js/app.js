App = Ember.Application.create({
    isEmpty: function isEmpty(str) {
        return (!str || 0 === str.length);
    }
});

App.Router.map(function() {
    this.resource('confirmation');
});

App.ConfirmationRoute = Ember.Route.extend({
});

App.ONEWAYROUTE = "One way";
App.ROUNDTRIP = "Roundtrip";
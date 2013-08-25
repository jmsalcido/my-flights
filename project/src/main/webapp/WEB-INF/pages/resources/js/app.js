App = Ember.Application.create({
    isEmpty: function isEmpty(str) {
        return (!str || 0 === str.length);
    }
});

App.Router.map(function() {
    this.route('confirmation');
});

App.ONEWAYROUTE = "One way";
App.ROUNDTRIP = "Roundtrip";
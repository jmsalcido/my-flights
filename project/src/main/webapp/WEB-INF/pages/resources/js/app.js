App = Ember.Application.create({
    isEmpty: function isEmpty(str) {
        return (!str || 0 === str.length);
    }
});

App.Router.map(function() {
    this.route('confirmation');
    this.route('confirmed');
    this.route('checkReservation', {path: "/check"});
});

App.ONEWAYROUTE = "One way";
App.ROUNDTRIP = "Roundtrip";
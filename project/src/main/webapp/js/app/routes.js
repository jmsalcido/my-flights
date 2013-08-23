App.ConfirmationRoute = Ember.Route.extend({
    serialize: function(model) {
        return model;
    },
    setupController: function(controller, model) {
        var content = this.controllerFor('flightInformation').get('content');

        controller.set('title', 'Flight Confirmation');
        controller.set('content', content);
    }
});
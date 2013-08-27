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

App.ConfirmedRoute = Ember.Route.extend({
    setupController: function(controller, model) {
        debugger;
        controller.set('model', model);
    }
});

App.ConfirmedController = Ember.Controller.extend({

});
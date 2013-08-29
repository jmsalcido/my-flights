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

App.IndexRoute = Ember.Route.extend({
    setupController: function() {
        this.controllerFor('router').setUp();
    }
});

App.ConfirmedRoute = Ember.Route.extend({
    setupController: function(controller) {
        var model = this.controllerFor('signup').get('model'),
            signupController = this.controllerFor('signup');
        signupController.set('model', null);
        controller.set('model', model);
    }
});
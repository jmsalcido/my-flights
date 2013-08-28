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
    setupController: function(controller) {
        debugger;
        var model = this.controllerFor('signup').get('model'),
            signupController = this.controllerFor('signup');
        signupController.set('model', null);
        controller.set('model', model);
    }
});

App.ConfirmedController = Ember.Controller.extend({
    modelChanged: function() {
        console.log('model has changed.');
    }.observes('model')
});

App.ConfirmedView = Ember.View.extend({
    tagName: "div",
    templateName: "confirmed"
});
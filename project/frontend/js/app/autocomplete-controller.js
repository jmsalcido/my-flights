App.AutocompleteController = Ember.Controller.extend({
  search: function(term, context) {
    var results = [
      Ember.Object.create({name: 'Bison'}),
      Ember.Object.create({name: 'Vega'}),
    ];
    context.set('content', results);
  }
});
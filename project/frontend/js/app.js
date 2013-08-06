App = Ember.Application.create();

// extend ember textfield to accept autocomplete and other attributes
App.TextField = Ember.TextField.extend({
    attributeBindings: ['accept', 'autocomplete', 'autofocus', 'name', 'required']
});

App.RoutesView = Ember.View.extend();

App.RoutesController = Ember.Controller.extend({
    airports: null,
    departureText: null,
    arrivalText: null,
    title: 'Select your route',
    searchResults: function(){
        var searchText = this.get('departureText') || this.get('arrivalText');
        if(!searchText) { return; }
        // server request
        var search = App.Search.find(searchText);
        var airports = search.get('airports');
        return airports;
    }.property('departureText', 'arrivalText'),
    select: function(airport) {
        this.set('departureText', airport.get('code'));
        this.get('departureText').enabled = 'false';
    }
});

App.AutocompleteView = Ember.View.extend({
    isInvisible: function() {
        var searchResults = this.get('searchResults');
        return searchResults !== null ? "invisible" : "visible";
    }
});

// PLEASE DELETE ME
// PLEASE DELETE ME
// PLEASE DELETE ME
App.Person = Ember.Object.extend({
    id: 0,
    first_name: null,
    last_name: null,
    fullName: function() {
        return this.get('first_name') + " " + this.get('last_name');
    }.property('first_name', 'last_name')
});

App.TestController = Ember.Controller.extend({
    selected: null,
    select: null,
    checkBox_disabled: true,
    disableCheckBox: function() {
        console.log(checkBox_disabled);
    }.observes('checkBox_disabled'),
    people: [],
    tmpFirstName: null,
    tmpLastName: null,
    // check box
    createPerson: false,
    _createPerson: function() {
        var createPerson = this.get('createPerson');
        console.log('createPerson changed to %@'.fmt(createPerson));
    }.observes('createPerson'),
    moveAlong: function() {
        var first_name = this.get('selected').get('first_name');
        var last_name = this.get('selected').get('last_name');
        this.set('tmpFirstName', first_name);
        this.set('tmpLastName', last_name);
    }.observes('selected'),
    usePerson: function(createPerson) {
        if(createPerson) {
            var person = App.Person.create();
            person.set('first_name', this.get('tmpFirstName'));
            person.set('last_name', this.get('tmpLastName'));
            person.addObserver('fullName', function() {
                console.log("%@: Y U CHANGE MUH NAME?".fmt(this.get('fullName')));
            });
            // PUSHOBJECT() here.
            this.get('people').pushObject(person);
            console.log(person.get('fullName') + " says: hello!");

            // reset inputs.
            this.set('tmpFirstName', null);
            this.set('tmpLastName', null);
            this.set('createPerson', false);
        } else {
            console.log(this.get('checkBox'));
            console.log('fuck you')
        }
    }
});

App.OTFusionLinkView = Ember.View.extend({
    tagName: "a",
    attributeBindings: ['href'],
    href: "http://otfusion.org"
});

App.ApplicationController = Ember.Controller.extend({
});

// App.AutoComplete = JQ.AutoComplete.extend({
//     source: function (e) {
//         var query = $('#tags').val(),
//             resultList = $('#results'),
//             print = function (res_obj){
//                 var array = res_obj.airports,
//                     length = res_obj.airports.length;
//
//                 for(var i = 0; i < length; i += 1){
//                     var country = array[i].name;
//                     console.log(array[i]);
//                     //TODO: replace this approach... Better if you clone the node, append all that shit and then, replace the original...
//                     resultList.append($('<li></li>').text(country));
//                 }
//                 $('#tags').autocomplete({
//             source: array
//         });
//             };
//         resultList.html('');
//         jQuery.App.SearchFn(query, print);
//     }
// });

App.RESTSerializer = DS.RESTSerializer.extend({
  init: function() {
    this._super();
}
});

App.Search = DS.Model.extend({
  airports: DS.hasMany('App.Airport')
});

App.Airport = DS.Model.extend({
  code: DS.attr('string'),
  name: DS.attr('string'),
  city: DS.attr('string'),
  country: DS.attr('string')
});

DS.RESTAdapter.configure("plurals", {
  search: "search"
});

DS.RESTAdapter.configure('App.Airport', {
  sideloadsAs: 'airports'
});

App.Adapter = DS.RESTAdapter.extend();


App.Store = DS.Store.extend({
  revision: 12,
  adapter: App.Adapter.create({
    mappings: { 
      airports: App.Airport,
      search: App.Search
  },
  url: 'http://localhost:8080',
  namespace: 'MyFlights',
  serializer: App.RESTSerializer
})
});

// $(function($){

//     $.App = $.App || {};

//     $.App.SearchFn = function (query, fn) {
//         $.ajax({
//                 url: 'http://localhost:8080/MyFlights/search/' + query
//             })
//             .done(function (e){
//                 fn.call(this, JSON.parse(e));
//             })
//             .error(function (e){});
//     };

// }(jQuery));
App = Ember.Application.create();

// extend ember textfield to accept autocomplete and other attributes
App.TextField = Ember.TextField.extend({
    attributeBindings: ['accept', 'autocomplete', 'autofocus', 'name', 'required']
});

App.DatePicker = Ember.TextField.extend({
    classNames: ['date-picker'],
    textToDateTransform: (function(key, value) {
        var date, month, parts;
        if (arguments.length === 2) {
            if (value instanceof Date) {
                this.set('date', date);
                return this.close();
            } else if (value && /\d{4}-\d{2}-\d{2}/.test(value)) {
                parts = value.split('-');
                date = new Date();
                date.setYear(parts[0]);
                date.setMonth(parts[1] - 1);
                date.setDate(parts[2]);
                this.set('date', date);
                return this.close();
            } else {
                return this.set('date', null);
            }
        } else if (arguments.length === 1 && this.get('date')) {
            month = this.get('date').getMonth() + 1;
            date = this.get('date').getDate();
            if (month < 10) {
                month = "0" + month;
            }
            if (date < 10) {
                date = "0" + date;
            }
            return "%@-%@-%@".fmt(this.get('date').getFullYear(), month, date);
        }
    }).property(),
    format: "yyyy-mm-dd",
    placeholder: Ember.computed.alias('format'),
    size: 8,
    valueBinding: "textToDateTransform",
    yesterday: (function() {
        var date;
        date = new Date();
        date.setDate(date.getDate() - 1);
        return date;
    }).property(),
    didInsertElement: function() {
        var _this = this;
        return this.$().datepicker({
            format: this.get('format'),
            autoclose: true,
            startDate: "today",
            todayHighlight: true,
            keyboardNavigation: true
        }).on('changeDate', function(ev) {
            _this.set('date', ev.date);
            console.log(_this.get('startDate'));
            return _this.$().datepicker('setValue', ev.date);
        });
    },
    close: function() {
        return this.$().datepicker('hide');
    }
});

App.RoutesView = Ember.View.extend();

App.RoutesController = Ember.Controller.extend({
    title: 'Select your route',
    airports: null,
    departureText: null,
    arrivalText: null,
    departureSelected: false,
    arrivalSelected: false,
    departureDate: new Date(),
    arrivalDate: '',
    searchResults:  function(){
        if(this.get('departureSelected')) {
            return;
        }
        var searchText = this.get('departureText') || this.get('arrivalText');
        if(!searchText) { return; }
        // server request
        var search = App.Search.find(searchText);
        return search.get('airports');
    }.property('departureText', 'arrivalText'),
    select: function(airport) {
        this.set('departureText', airport.get('code'));
        this.get('departureText').enabled = 'false';
        this.set('departureSelected', true);
    },
    search: function() {
        console.log("departureCode: " + this.get('departureText'));
        console.log("arrivalCode: " + this.get('arrivalText'));
        console.log("departureDate: " + this.get('departureDate'));
        console.log("arrivalDate: " + this.get('arrivalDate'));
    }
});

App.AutocompleteView = Ember.View.extend({
    classNameBindings:['isInvisible:invisible'],
    isInvisible: function(e) {
        var searchResults = this.get('controller').get('searchResults');
        var result = searchResults === undefined;
        return result;
    }.property('controller.searchResults', 'controller.departureSelected', 'controller.arrivalSelected')
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

// App.TestController = Ember.Controller.extend({
//     selected: null,
//     select: null,
//     checkBox_disabled: true,
//     disableCheckBox: function() {
//         console.log(checkBox_disabled);
//     }.observes('checkBox_disabled'),
//     people: [],
//     tmpFirstName: null,
//     tmpLastName: null,
//     // check box
//     createPerson: false,
//     _createPerson: function() {
//         var createPerson = this.get('createPerson');
//         console.log('createPerson changed to %@'.fmt(createPerson));
//     }.observes('createPerson'),
//     moveAlong: function() {
//         var first_name = this.get('selected').get('first_name');
//         var last_name = this.get('selected').get('last_name');
//         this.set('tmpFirstName', first_name);
//         this.set('tmpLastName', last_name);
//     }.observes('selected'),
//     usePerson: function(createPerson) {
//         if(createPerson) {
//             var person = App.Person.create();
//             person.set('first_name', this.get('tmpFirstName'));
//             person.set('last_name', this.get('tmpLastName'));
//             person.addObserver('fullName', function() {
//                 console.log("%@: Y U CHANGE MUH NAME?".fmt(this.get('fullName')));
//             });
//             // PUSHOBJECT() here.
//             this.get('people').pushObject(person);
//             console.log(person.get('fullName') + " says: hello!");

//             // reset inputs.
//             this.set('tmpFirstName', null);
//             this.set('tmpLastName', null);
//             this.set('createPerson', false);
//         } else {
//             console.log(this.get('checkBox'));
//             console.log('fuck you')
//         }
//     }
// });

// App.ApplicationController = Ember.Controller.extend({
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
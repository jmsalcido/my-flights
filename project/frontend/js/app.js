function isEmpty(str) {
    return (!str || 0 === str.length);
}

App = Ember.Application.create();

// ===============================
// VIEWS.JS
// ===============================
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
            return _this.$().datepicker('setValue', ev.date);
        });
    },
    close: function() {
        return this.$().datepicker('hide');
    }
});

App.AutocompleteTextField = Ember.TextField.extend({
    attributeBindings: ['accept', 'autocomplete', 'autofocus', 'name', 'required'],
    focusOut: function(e) {
        //this.get('controller').set('isAutoCompletedInvisible', true);
        // this kills the event at the controller.
    },
    keyUp: function(e) {
        if(e.keyCode == 27) {
            this.get('controller').set('isAutoCompletedInvisible', true);
        }
    },
    searchDepartures: function(x) {
        var controller = this.get('controller');
        var departureCode = controller.get('departureCode');
        var departureText = controller.get('departureText');
        var departureSelected = controller.get('departureSelected');

        if(departureCode === departureText) {
            controller.set('isAutoCompletedInvisible', true);
            return;
        }

        // finally I hope this works.
        controller.send('searchAirports', departureText);
    }.observes('controller.departureText'),
    searchArrivals: function(x) {
        var controller = this.get('controller');
        var arrivalCode = controller.get('arrivalCode');
        var arrivalText = controller.get('arrivalText');
        var arrivalSelected = controller.get('arrivalSelected');

        if(arrivalCode === arrivalText) {
            controller.set('isAutoCompletedInvisible', true);
            return;
        }

        // finally I hope this works.
        controller.send('searchAirports', arrivalText);
    }.observes('controller.arrivalText'),
    keyPress: function(e) {
    }
});

App.AutocompleteView = Ember.View.extend({
    attributeBindings: ['name'],
    name: null,
    templateName: 'autocomplete',
    classNameBindings:['isInvisible:invisible'],
    isInvisible: function(params) {
        return this.get('controller').get('isAutoCompletedInvisible');
    }.property('controller.isAutoCompletedInvisible')
});

// ===============================
// ROUTES.JS (change)
// ===============================
App.RoutesController = Ember.Controller.extend({
    searchAirports: function(text) {
        if(!text || isEmpty(text)) {
            this.set('isAutoCompletedInvisible', true);
            return;
        } else {
            this.set('isAutoCompletedInvisible', false);
        }
        // server request
        var search = App.Search.find(text);
        this.set('searchResults', search.get('airports'));
    },
    selectAirport: function(text) {

    },
    title: 'Select your route',
    departureText: null,
    arrivalText: null,
    departureSelected: false,
    arrivalSelected: false,
    departureDate: new Date(),
    arrivalDate: null,
    departureCode: '',
    arrivalCode: '',
    isAutoCompletedInvisible: true,
    isAutoCompletedFocused: false,
    arrivalDate: null,
    searchResults:  '',
    searchResultsAlternativeText: function() {
        var search = this.get('searchResults');
        if(!search) {
            return "Could not find airports with that keyword..."
        } else {
            return "Loading..."
        }
    }.property(),
    searchFlights: function() {
        console.log("departureCode: " + this.get('departureCode'));
        console.log("arrivalCode: " + this.get('arrivalCode'));
        console.log("departureDate: " + this.get('departureDate'));
        console.log("arrivalDate: " + this.get('arrivalDate'));
    },
    selectAirportCode: function(airport, isOrigin) {
        
        this.set('isAutoCompletedInvisible', true);
        var code = airport.get('code');
        var selected = this.get('departureSelected');
        if(!selected) {
            this.set('departureCode', code);
            this.set('departureSelected', true);
            this.set('departureText', code);
            return;
        }

        selected = this.get('arrivalSelected');

        if(!selected) {
            this.set('arrivalCode', code);
            this.set('arrivalSelected', true);
            this.set('arrivalText', code);
            return;
        }
    }
});

// ===============================
// MYFLIGHTS-DATA.js
// ===============================
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
App = Ember.Application.create({
    isEmpty: function isEmpty(str) {
        return (!str || 0 === str.length);
    }
});

App.Router.map(function() {
});


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

App.RoutesView = Ember.View.extend({});

App.AutocompleteTextField = Ember.TextField.extend({
    attributeBindings: ['accept', 'autocomplete', 'autofocus', 'name', 'required'],
    focusOut: function(e) {
        // TODO
        //this.get('controller').set('isAutoCompletedInvisible', true);
        // this kills the event at the controller.
    },
    keyUp: function(e) {
        if(e.keyCode == 27) {
            this.get('controller').set('isAutoCompletedInvisible', true);
        }
    },
    searchInTextFields: function(controller, code, text, selected) {
        if(code === text) {
            controller.set('isAutoCompletedInvisible', true);
            return;
        }
        controller.send('searchAirports', text);
    },
    searchDepartures: function(x) {
        var controller = this.get('controller');

        controller.set('isDeparture', true);

        this.searchInTextFields(controller,
            controller.get('departureCode'),
            controller.get('departureText'),
            controller.get('departureSelected'));
    }.observes('controller.departureText'),
    searchArrivals: function(x) {
        var controller = this.get('controller');

        controller.set('isDeparture', false);

        this.searchInTextFields(controller,
            controller.get('arrivalCode'),
            controller.get('arrivalText'),
            controller.get('arrivalSelected'));
    }.observes('controller.arrivalText')
});

App.AutocompleteView = Ember.View.extend({
    templateName: 'autocomplete',
    classNameBindings:['isInvisible:invisible'],
    isInvisible: function(params) {
        return this.get('controller').get('isAutoCompletedInvisible');
    }.property('controller.isAutoCompletedInvisible'),
    click: function() {
    }
});

App.FlightView = Ember.View.extend({
    classNameBindings:['isInvisible:invisible'],
    isInvisible: function() {
        return this.get('controller').get('isInvisible');
    }.property('controller.isInvisible')
});

App.FlightsListView = Ember.View.extend({
    templateName: "flightsList"
});

App.FlightDetailView = Ember.View.extend({
    templateName: "flightDetail"
});

// ===============================
// ROUTES.JS (change)
// ===============================
App.IndexController = Ember.Controller.extend({
});

App.RouterController = Ember.Controller.extend({
    needs: ['flights'],
    searchAirports: function(text) {
        if(!text || App.isEmpty(text)) {
            this.set('isAutoCompletedInvisible', true);
            return;
        } else {
            this.set('isAutoCompletedInvisible', false);
        }
        // server request
        var airportSearch = App.SearchAirport.find(text);
        this.set('searchResults', airportSearch.get('airports'));
    },
    title: 'Select your route',
    isDeparture: null,
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
    selectAirport: function(airport) {
        this.set('isAutoCompletedInvisible', true);
        var code = airport.get('code');
        var selected = this.get('departureSelected');
        var airport = null;
        if(this.get('isDeparture')) {
            this.set('departureCode', code);
            this.set('departureSelected', true);
            this.set('departureText', code);
        } else {
            this.set('arrivalCode', code);
            this.set('arrivalSelected', true);
            this.set('arrivalText', code);
        }
    },
    searchFlights: function() {
        // create the flight model.
        console.log("departureCode: " + this.get('departureCode'));
        console.log("arrivalCode: " + this.get('arrivalCode'));
        console.log("departureDate: " + this.get('departureDate'));
        console.log("arrivalDate: " + this.get('arrivalDate'));

        var route = App.RouteModel.create({
            departureCode: this.get('departureCode'),
            arrivalCode: this.get('arrivalCode'),
            departureDate: this.get('departureDate'),
            arrivalDate: this.get('arrivalDate')
        });

        // set visible the flight view.
        this.get('controllers.flights').set('model', route);
    }
});

App.FlightsController = Ember.Controller.extend({
    needs: ['router'],
    title: 'Flights',
    isInvisible: true,
    flightResults: function() {
        var model = this.get('model');
        if(typeof model == 'undefined') {
            return;
        }
        var date = moment(model.get('departureDate')).format('YYYY-MM-DD');
        var flights = [];
        var search_params = "help";
        var search_params = 'search?f=%@&t=%@&d=%@'.fmt(
                            model.get('departureCode'),
                            model.get('arrivalCode'),
                            date);
        flights = App.SearchFlight.find(search_params);
        this.set("isInvisible", false);
        return flights.get('flights');
    }.property('model'),
    testModel: function() {
        console.log(this.get('model').get('departureCode'));
    }
});

// ===============================
// MYFLIGHTS-DATA.js
// ===============================
App.RouteModel = Ember.Object.extend({
    departureCode: '',
    arrivalCode: '',
    departureDate: '',
    arrivalDate: ''
});

App.RESTSerializer = DS.RESTSerializer.extend({
    init: function() {
        this._super();
    }
});

App.SearchAirport = DS.Model.extend({
  airports: DS.hasMany('App.Airport')
});

App.SearchFlight = DS.Model.extend({
  flights: DS.hasMany('App.Flight')
});

App.Airport = DS.Model.extend({
  code: DS.attr('string'),
  name: DS.attr('string'),
  city: DS.attr('string'),
  country: DS.attr('string')
});

App.FlightDetail = DS.Model.extend({
    departure_airport: DS.attr('string'),
    arrival_airport: DS.attr('string'),
    departure_time: DS.attr('string'),
    arrival_time: DS.attr('string'),
    travel_time: DS.attr('number'),
    flight_number: DS.attr('string')
});

// App.Flight = DS.Model.extend({
//     code: DS.attr('string')
//});

App.Flight = DS.Model.extend({
    date: DS.attr('string'),
    departureAirport: DS.attr('string'),
    arrivalAirport: DS.attr('string'),
    travelTime: DS.attr('number'),
    flightType: DS.attr('number'),
    flightDetail: DS.hasMany('App.FlightDetail')
});

DS.RESTAdapter.configure("plurals", {
  search_airport: "search_airports",
  search_flight: "search_flights",
  flight: "flights"
});

DS.RESTAdapter.configure('App.Airport', {
  sideloadsAs: 'airports'
});

DS.RESTAdapter.configure('App.Flight', {
  sideloadsAs: 'flights'
});

App.Adapter = DS.RESTAdapter.extend();


App.Store = DS.Store.extend({
  revision: 12,
  adapter: App.Adapter.create({
    mappings: { 
      airports: App.Airport,
      flights: App.Flight,
      flight_detail: App.FlightDetail,
      search_airport: App.SearchAirport,
      search_flight: App.SearchFlight
  },
  url: 'http://localhost:8080',
  namespace: 'MyFlights',
  serializer: App.RESTSerializer
})
});
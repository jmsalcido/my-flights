App.IndexController = Ember.Controller.extend({
});

App.RouterController = Ember.Controller.extend({
    needs: ['flights'],
    searchAirports: function(text) {
        var self = this;
        if(!text || App.isEmpty(text)) {
            this.set('isAutoCompletedInvisible', true);
            return;
        } else {
            this.set('isAutoCompletedInvisible', false);
        }
        // server request
        var airportSearch = App.SearchAirport.find(text).then(function(model){
            self.set('searchResults', model.get('airports'));
        });
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
    departureAirport: null,
    arrivalAirport: null,
    isAutoCompletedInvisible: true,
    isAutoCompletedFocused: false,
    arrivalDate: null,
    searchResults:  '',
    routeTypes: ['One way', 'Roundtrip'],
    routeType: 'oneway', // default behaviour
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
        // var selected = this.get('departureSelected');
        if(this.get('isDeparture')) {
            this.set('departureAirport', airport);
            this.set('departureCode', code);
            this.set('departureSelected', true);
            this.set('departureText', code);
        } else {
            this.set('arrivalAirport', airport);
            this.set('arrivalCode', code);
            this.set('arrivalSelected', true);
            this.set('arrivalText', code);
        }
    },
    searchFlightsAction: function() {
        var route;
        // TODO some validations here
        // b4creating the route.

        route = App.RouteModel.create({
            departureAirport: this.get('departureAirport'),
            arrivalAirport: this.get('arrivalAirport'),
            departureDate: this.get('departureDate'),
            arrivalDate: this.get('arrivalDate')
        });

        // set visible the flight view.
        this.get('controllers.flights').set('model', route);
    },
    removeOrDisplayRoute: function() {
        var routeSelection = this.get('routeSelection'),
            routeTypes = this.get('routeTypes');
        console.log(routeSelection);

        // Single must remove the "date" value from the arrivalDate
        if(routeSelection === routeTypes[0]) {
            // TODO remove the arrivalDate text :)
            this.set('arrivalDate', null);
            this.set('routeType', 'oneway');
        } else {
            this.set('routeType', 'roadtrip');
        }
    }.observes('routeSelection')
});

App.FlightsController = Ember.Controller.extend({
    needs: ['router', 'flightInformation'],
    title: 'Flights',
    selectedFlights: [], // this will save the selected flights that will be "recorded"
    isInvisible: true,
    flightResults: null,
    flightResultsObserver: function() {
        console.log('so im going to search something huh...');
        var model = this.get('model');
        if(typeof model == 'undefined') {
            console.log('i cant search that.');
            return;
        }
        var search,
            date = moment(model.get('departureDate')).format('YYYY-MM-DD'),
            self = this,
            departureAirport = model.get('departureAirport'),
            arrivalAirport = model.get('arrivalAirport'),
            search_params = "help",
            search_params = 'search?f=%@&t=%@&d=%@'.fmt(
                            departureAirport.get('code'),
                            arrivalAirport.get('code'),
                            date);
        this.get('model').set('date', date);
        App.SearchFlight.find(search_params).then(function(searchModel) {
            console.log('finally!');
            self.set("isInvisible", false);
            self.set('flightResults', searchModel.get('flights'));
        });
        
    }.observes('model'),
    selectFlight: function(flight) {
        var router = this.get('controllers.router'),
            routeType = router.get('routeType'),
            model = this.get('model'),
            aux = null,
            flightInformation = null,
            flightsInformation = [],
            selectedFlights = this.get('selectedFlights');

        flightInformation = App.RouteModel.create({
            departureAirport: model.get('departureAirport'),
            arrivalAirport: model.get('arrivalAirport'),
            flight: flight
        });

        if(routeType = router.get('routeTypes')[0]) {
            // single
            // should show the "an agent will contact you soon advertise (for the moment)".
            this.set("isInvisible", true);
            this.get('controllers.flightInformation').send('addElement', flightInformation)
        } else {
            // roundtrip

            //check if is there any flightSelected.
            //if(selectedFlights.length === 0){};
            // flip the departure and arrival
            selectedFlights.pushObject(flightInformation);
            this.set("isInvisible", true);
            aux = model.get('departureAirport');
            model.set('departureAirport', model.get('arrivalAirport'));
            model.set('arrivalAirport', aux);
            //else{}
        }
    }
});

App.FlightInformationController = Ember.ArrayController.extend({
    content: [],
    title: "Flight Information",
    isInvisible: true,
    addElement: function(element) {
        this.get('content').pushObject(element);
    },
    showInformation: function() {
        this.set('isInvisible', false);
    }.observes('content.@each')
});
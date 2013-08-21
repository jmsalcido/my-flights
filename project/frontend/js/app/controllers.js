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
    routeTypes: ['One way', 'Roundtrip'],
    routeType: 'oneway',
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
    searchFlightsAction: function() {

        var route = App.RouteModel.create({
            departureCode: this.get('departureCode'),
            arrivalCode: this.get('arrivalCode'),
            departureDate: this.get('departureDate'),
            arrivalDate: this.get('arrivalDate')
        });

        // TODO some validations here

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
    needs: ['router'],
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
            search_params = "help",
            search_params = 'search?f=%@&t=%@&d=%@'.fmt(
                            model.get('departureCode'),
                            model.get('arrivalCode'),
                            date);
        this.get('model').set('date', date);
        App.SearchFlight.find(search_params).then(function(searchModel) {
            console.log('finally!');
            self.set("isInvisible", false);
            self.set('flightResults', searchModel.get('flights'));
        });
        
    }.observes('model'),
    selectFlight: function(flight) {

    }
});
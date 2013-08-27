App.IndexController = Ember.Controller.extend({
});

App.HeaderController = Ember.Controller.extend({
    redirection: function() {
        this.transitionToRoute('index');
    }
});

App.RouterController = Ember.Controller.extend({
    needs: ['flights', 'flightInformation'],
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
    arrivalDateText: null,
    searchResults:  '',
    routeTypes: [App.ONEWAYROUTE, App.ROUNDTRIP],
    routeType: App.ONEWAYROUTE, // default behaviour
    searchResultsAlternativeText: function() {
        var search = this.get('searchResults');
        if(!search) {
            return "Could not find airports with that keyword...";
        } else {
            return "Loading...";
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
        var route, flightsController = this.get("controllers.flights"),
            flightInformationController = this.get('controllers.flightInformation');

        flightInformationController.set('isInvisible', true);
        flightsController.set('selectedFlights', []);
        flightInformationController.clear();
        flightsController.set('title', "Select your departure flight");
        // TODO some validations here
        // b4creating the route.

        route = App.RouteModel.create({
            departureAirport: this.get('departureAirport'),
            arrivalAirport: this.get('arrivalAirport'),
            departureDate: this.get('departureDate'),
            arrivalDate: this.get('arrivalDate')
        });

        // set visible the flight view.
        flightsController.set('model', route);
    },
    routeSelection: App.ONEWAYROUTE,
    removeOrDisplayRoute: function() {
        var routeSelection = this.get('routeSelection'),
            routeTypes = this.get('routeTypes');
        console.log(routeSelection);

        // Single must remove the "date" value from the arrivalDate
        if(routeSelection === App.ONEWAYROUTE) {
            // TODO remove the arrivalDate text :)
            this.set('arrivalDate', null);
            this.set('arrivalDateText', null);
            this.set('routeType', App.ONEWAYROUTE);
        } else {
            this.set('routeType', App.ROUNDTRIP);
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
        console.log('searching...');
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
            console.log('done searching');
            self.set("isInvisible", false);
            self.set('flightResults', searchModel.get('flights'));
        });
        
    }.observes('model'),
    selectFlight: function(flight) {
        var router = this.get('controllers.router'),
            routeType = router.get('routeType'),
            model = this.get('model'),
            backRoute = null,
            flightInformation = null,
            selectedFlights = this.get('selectedFlights'),
            self = this;

        flightInformation = App.FlightInformation.create({
            routeType: routeType,
            departureAirport: model.get('departureAirport'),
            arrivalAirport: model.get('arrivalAirport'),
            flight: flight
        });

        if(routeType === App.ONEWAYROUTE) {
            // single
            // should show the "an agent will contact you soon advertise (for the moment)".
            this.set("isInvisible", true);
            flightInformation.isDeparture = true;
            this.get('controllers.flightInformation').send('addElement', flightInformation);

        } else {
            // roundtrip

            //check if is there any flightSelected.
            if(selectedFlights.length === 0){

                backRoute = App.RouteModel.create({
                    departureAirport: model.get('arrivalAirport'),
                    arrivalAirport: model.get('departureAirport'),
                    departureDate: model.get('arrivalDate'),
                    arrivalDate: model.get('departureDate')
                });

                // flip the departure and arrival
                flightInformation.isDeparture = true;
                selectedFlights.pushObject(flightInformation);
                this.set("isInvisible", true);
                this.get('controllers.flightInformation').send('addElement', flightInformation);
                this.set('title', "Select your return flight");
                this.set('model', backRoute);
            }
            else{
                // second selection
                flightInformation.isDeparture = false;
                this.set("isInvisible", true);
                this.get('controllers.flightInformation').send('addElement', flightInformation);
            }
        }
    }
});

App.FlightInformationController = Ember.ArrayController.extend({
    needs: ["router"],
    title: "Flight Information",
    isInvisible: true,
    addElement: function(element) {
        this.get('content').addObject(element);
    },
    showInformation: function() {
        this.set('isInvisible', this.get('content').length === 0);
    }.observes('content.@each'),
    showConfirmationButton: function() {
        var routerController = this.get('controllers.router');
        if(routerController.get('routeType') === App.ONEWAYROUTE) {
            return true;
        } else {
            return this.get('content.length') === 2;
        }
    }.property('content.@each'),
    confirmFlight: function() {
        var content = this.get('content'),
            confirmation = Ember.Object.create({
                flightInformation: content
            });
        this.transitionToRoute('confirmation');
    }
});

App.ConfirmationController = Ember.ArrayController.extend({

});

App.SignupController = Ember.Controller.extend({
    needs: ['confirmation'],
    nameText: null,
    lastNameText: null,
    telephoneText: null,
    emailText: null,
    isLoading: false,
    changeLoadingStatus: function(status) {
        if(typeof status === "boolean" | status === null) {
            this.set('isLoading', status);
        }
    },
    didCreateReservation: function(controller) {
        console.log('Reservation created');
        controller.changeLoadingStatus(null);
        // Redirect here.
    },
    createReservationRecord: function() {
        var reservation = App.Reservation.createRecord(),
            self = this;

        // callbacks
        reservation.on('didCreate', function() {
            self.didCreateReservation(self);
        });

        reservation.on('becameInvalid', function() {
            console.log("becameInvalid");
        });

        reservation.on('becameError', function() {
            console.log("becameError");
        });
        return reservation;
    },
    createReservation: function() {
        var reservation = null,
            isLoading = this.get('isLoading'),
            confirmationController = this.get('controllers.confirmation');

        if(confirmationController.get('content')) {

        }
        if(isLoading) {
            return;
        }

        // create the reservation record
        reservation = this.createReservationRecord();

        // set the reservation data
        reservation.set('name', this.get('nameText'));
        reservation.set('lastName', this.get('lastNameText'));
        reservation.set('email', this.get('emailText'));
        reservation.set('telephone', this.get('telephoneText'));

        // TODO hardcoded
        reservation.set('departure', 'MEX');
        reservation.set('arrival', 'CUL');
        reservation.set('departureDate', new Date());
        reservation.set('arrivalDate', new Date());
        reservation.set('price', 1000);
        reservation.save();
        this.changeLoadingStatus(true);
    }
});
App.RouteModel = Ember.Object.extend({
  routeType: null,
  departureAirport: null,
  arrivalAirport: null,
  departureDate: null,
  arrivalDate: null
});

App.FlightInformation = Ember.Object.extend({
  isDeparture: null,
  departureAirport: null,
  arrivalAirport: null,
  flight: null
})

App.RESTSerializer = DS.RESTSerializer.extend({
  init: function() {
    this._super();
  }
});

App.SearchAirport = DS.Model.extend({
  airports: DS.hasMany('App.Airport')
});

App.ErrorModel = DS.Model.extend({
  error: DS.attr('string')
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
  departureAirport: DS.attr('string'),
  arrivalAirport: DS.attr('string'),
  departureTime: DS.attr('string'),
  arrivalTime: DS.attr('string'),
  travelTime: DS.attr('number'),
  flightNumber: DS.attr('string'),
  airlineCode: DS.attr('string'),
  airlineName: DS.attr('string')
});

App.Flight = DS.Model.extend({
  date: DS.attr('string'),
  departureAirport: DS.attr('string'),
  arrivalAirport: DS.attr('string'),
  travelTime: DS.attr('number'),
  flightType: DS.attr('number'),
  flightDetails: DS.hasMany('App.FlightDetail')
});

App.Reservation = DS.Model.extend({
  price: DS.attr('number'),
  name: DS.attr('string'),
  lastName: DS.attr('string'),
  telephone: DS.attr('string'),
  email: DS.attr('string'),
  departure: DS.attr('string'),
  arrival: DS.attr('string'),
  departureDate: DS.attr('date'),
  arrivalDate: DS.attr('date'),
  reservationNumber: DS.attr('number')
})

DS.RESTAdapter.configure("plurals", {
  search_airport: "search_airports",
  search_flight: "search_flights",
  flight: "flights",
  flight_detail: "flight_details"
});

DS.RESTAdapter.configure('App.Airport', {
  sideloadsAs: 'airports'
});

DS.RESTAdapter.configure('App.Flight', {
  sideloadsAs: 'flights'
});

DS.RESTAdapter.map('App.Flight', {
  flightDetails: { embedded: 'always' }
});

App.Adapter = DS.RESTAdapter.extend();

App.Store = DS.Store.extend({
  revision: 12,
  adapter: App.Adapter.create({
    mappings: { 
      airports: App.Airport,
      flights: App.Flight,
      search_airport: App.SearchAirport,
      search_flight: App.SearchFlight,
      error: App.ErrorModel
    },
    namespace: 'myflights',
    serializer: App.RESTSerializer
  })
});
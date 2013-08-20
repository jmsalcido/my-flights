Ember.Handlebars.helper('googleAirline', function(airlineCode) {
    var escaped = Handlebars.Utils.escapeExpression(airlineCode);
    return new Handlebars.SafeString('<img src="https://www.gstatic.com/flights/airline_logos/16px/' + escaped +  '.png"');
});

Ember.Handlebars.helper('flightTime', function(flight) {
    var travelTime = flight.get('travelTime');
    return (travelTime / 60).toFixed(2);
});

Ember.Handlebars.helper('flightDepartureTime', function(flight) {
    var flightDetails = flight.get('flightDetails'),
        firstFlight = flightDetails.get('firstObject');

    return firstFlight.get('departureTime');
});

Ember.Handlebars.helper('flightArrivalTime', function(flight) {
    var flightDetails = flight.get('flightDetails'),
        lastFlight = flightDetails.get('lastObject');

    return lastFlight.get('arrivalTime');
});
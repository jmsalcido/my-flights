Ember.Handlebars.helper('googleAirline', function(airlineCode) {
    var escaped = Handlebars.Utils.escapeExpression(airlineCode);
    return new Handlebars.SafeString('<img src="https://www.gstatic.com/flights/airline_logos/16px/' + escaped +  '.png"');
});

Ember.Handlebars.helper('flightTime', function(travelTime) {
    var travelHours = Math.floor(travelTime / 60),
        travelMinutes = travelTime % 60;

    return [travelHours, travelMinutes].join(':');
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

Ember.Handlebars.helper('formatFullDate', function(date) {
    return moment(date).format('YYYY-MM-DD');
});

Ember.Handlebars.helper('formatAMPM', function(time) {
    var hours, minutes, ampm, timeArray;
    timeArray = time.split(":");
    hours = parseInt(timeArray[0]);
    minutes = parseInt(timeArray[1]);
    ampm = hours >= 12 ? 'pm' : 'am';
    hours = hours % 12;
    hours = hours !== 0 ? hours : 12;
    minutes = minutes < 10 ? '0'+minutes : minutes;
    return hours + ':' + minutes + ' ' + ampm;
});

Ember.Handlebars.helper('departureOrArrival', function(isDeparture){
    if(isDeparture) {
        return "Departure";
    }
    return "Arrival";
});

Ember.Handlebars.helper('totalPrice', function(content){
    var totalPrice = 0,
        i = 0;
    for(i = 0; content.length; i += 1) {
        // TODO price at model is not yet created.
        totalPrice = content.get('flight').get('id');
    }
    return totalPrice;
});
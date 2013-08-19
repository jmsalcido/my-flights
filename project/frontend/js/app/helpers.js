Ember.Handlebars.helper('google-airline', function(airlineCode) {
    var escaped = Handlebars.Utils.escapeExpression(airlineCode);
    return new Handlebars.SafeString('<img src="https://www.gstatic.com/flights/airline_logos/16px/' + escaped +  '.png"');
})
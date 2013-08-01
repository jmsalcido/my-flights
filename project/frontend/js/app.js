App = Ember.Application.create();

JQ.AutoComplete = Em.TextField.extend(JQ.Widget, {
  uiType: 'autocomplete',
  uiOptions: ['source', 'autoFocus', 'delay', 'disabled', 'minLength', 'position']
});

JQ.ButtonView = Em.View.extend(JQ.Widget, {
  uiType: 'button',
  uiOptions: ['label', 'disabled'],
  tagName: 'button'
});

App.ButtonView = JQ.ButtonView.extend({
  click: function() {
    this.set('disabled', true);
    this.get('controller').consoleIt('huehuehue');
  }
});

App.RoutesView = Ember.View.extend();

App.RoutesController = Ember.Controller.extend({
  airports: null,
  departureText: null,
  arrivalText: null,
  testText: "",
  autocomplete: function(request, response) {
    response([request.term]);
  },
  searchResults: function(){
    var searchText = this.get('departureText');
    if(!searchText) { return; }
    var search = App.Search.find(searchText);
    var airports = search.get('airports');
    return airports;
  }.property('departureText'),
  consoleIt: function(param) {
    console.log(param);
  }
})

App.AutoComplete = JQ.AutoComplete.extend({
    source: function (e) {
        var query = $('#tags').val(),
            resultList = $('#results'),
            print = function (res_obj){
                var array = res_obj.airports,
                    length = res_obj.airports.length;

                for(var i = 0; i < length; i += 1){
                    var country = array[i].name;
                    console.log(array[i]);
                    //TODO: replace this approach... Better if you clone the node, append all that shit and then, replace the original...
                    resultList.append($('<li></li>').text(country));
                }
                $('#tags').autocomplete({
            source: array
        });
            };
        resultList.html('');
        jQuery.App.SearchFn(query, print);
    }
});

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

$(function($){

    $.App = $.App || {};

    $.App.SearchFn = function (query, fn) {
        $.ajax({
                url: 'http://localhost:8080/MyFlights/search/' + query
            })
            .done(function (e){
                fn.call(this, JSON.parse(e));
            })
            .error(function (e){});
    };

}(jQuery));
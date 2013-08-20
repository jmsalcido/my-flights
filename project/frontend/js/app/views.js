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
        // this kills the event at the controller.
        // this.get('controller').set('isAutoCompletedInvisible', true);
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
    }.property('controller.isAutoCompletedInvisible')
});

App.FlightsView = Ember.View.extend({
    classNameBindings:['isInvisible:invisible'],
    isInvisible: function() {
        return this.get('controller').get('isInvisible');
    }.property('controller.isInvisible')
});

App.FlightsListView = Ember.View.extend({
    templateName: "flightsList"
});

App.FlightView = Ember.View.extend({
    tagName: 'div',
    templateName: "flight",
});

App.FlightTimesView = Ember.View.extend({
  tagName: 'div',
  templateName: 'flightTimes',
  didInsertElement: function() {
    
  }
});

App.FlightDetailView = Ember.View.extend({
  tagName: 'div',
  templateName: 'flightDetail',
  didInsertElement: function() {
    
  }
});

App.FlightDetailRouteView = Ember.View.extend({
  tagName: 'div',
  templateName: 'flightDetailRoute',
  didInsertElement: function() {
    
  }
});

App.FlightDetailAirlineView = Ember.View.extend({
    tagName: 'div',
    templateName: 'flightDetailAirline',
    didInsertElement: function() {

    }
});

App.BookButtonView = Ember.View.extend({
    tagName: 'a',
    templateName: "bookButton",
    classNameBindings: ["isHover:btn"],
    isHover: false,
    mouseEnter: function() {
        this.set('isHover', true);
    },
    mouseLeave: function() {
        this.set('isHover', false);
    }
});
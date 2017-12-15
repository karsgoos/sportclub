$(document).ready(function() {
  $('.eventDatepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 120, // Creates a dropdown to control year,
    min: -47450,   //120 years
    max: -1825,    // 5 years
    today:false,
    monthsFull: ['Januari', 'Februari', 'Maart', 'April', 'Mei', 'Juni', 'Juli', 'Augustus', 'September', 'Oktober', 'November', 'December'],
    monthsShort: ['Jan', 'Feb', 'Mrt', 'Apr', 'Mei', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dec'],
    weekdaysFull: ['Zondag', 'Maandag', 'Dinsdag', 'Woensdag', 'Donderdag', 'Vrijdag', 'Zaterdag'],
    weekdaysShort: ['Zon', 'Maa', 'Din', 'Woe', 'Don', 'Vri', 'Zat'],
    weekday:['Z', 'M', 'D', 'W', 'D', 'V', 'Z'], //TODO This does not work yet
    labelMonthNext: 'Volgende maand',
    labelMonthPrev: 'Vorige maand',
    labelMonthSelect: 'Selecteer een maand',
    labelYearSelect: 'Selecteer een jaar',
    clear: false,
    close: 'Ok',
    closeOnSelect: false // Close upon selecting a date,
  });

  $('.timepicker').pickatime({
    default: 'now', // Set default time: 'now', '1:30AM', '16:30'
    fromnow: 0,       // set default time to * milliseconds from now (using with default = 'now')
    twelvehour: false, // Use AM/PM or 24-hour format
    donetext: 'OK', // text for done-button
    cleartext: 'Clear', // text for clear-button
    canceltext: 'Cancel', // Text for cancel-button
    autoclose: false, // automatic close timepicker
    ampmclickable: true, // make AM PM clickable
    aftershow: function () {
    } //Function for after opening timepicker
  });
});

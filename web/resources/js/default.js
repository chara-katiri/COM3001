/* Used for global document, navigator, $, ItemsList and Utils */

$(function () {

//Use of "use strict"; to define that JavaScript code should be executed in "strict mode" and undeclared variables cannot be used. 
//Source: W3schools.com, 'JavaScript "use strict"', 2015. [Online]. Available: http://www.w3schools.com/js/js_strict.asp. [Accessed: 10- Feb- 2015].
    "use strict";

    $.ajax({
        type: 'Get',
        url: Utils.getPageContext() + '/items',
        success: function (items) {
            $.each(items, function () {
                ItemsList.addItem(this);
            });
        }
    });

    Utils.setUpDynamicBackToTop();
});

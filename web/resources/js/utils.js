/*Used for global document, $,  and window */
var Utils = (function () {
    
    //Use of "use strict"; to define that JavaScript code should be executed in "strict mode" undeclared variables cannot be used..
    //W3schools.com, 'JavaScript "use strict"', 2015. [Online]. Available: http://www.w3schools.com/js/js_strict.asp. [Accessed: 10- Feb- 2015].
    "use strict";

    var isValidVariable, isValidNonEmptyString, isValidFn,
            getPageContext, getCsrfToken, setUpDynamicBackToTop;

    isValidVariable = function (variable) {
        return variable !== null && typeof variable !== 'undefined';
    };

    isValidNonEmptyString = function (string) {
        return isValidVariable(string) && typeof string === 'string' && string !== '';
    };

    isValidFn = function (fn) {
        return isValidVariable(fn) && typeof fn === 'function';
    };

    getPageContext = function () {
        var elem = document.querySelector("#pageContextPath");
        if (elem && $(elem).data()) {
            return $(elem).data("pageContext");
        }
        return "";
    };
    
    getCsrfToken = function () {
        var elem = document.querySelector("#csrfToken");
        if (elem && $(elem).data()) {
            return $(elem).data("csrfToken");
        }
        return "";
    };


// Smooth Back to top scrolling. 
// Source: Developerdrive.developerdrive.netdna-cdn.com, 'Adding a dynamic "Back To Top" floating button with smooth scroll', 2015. [Online]. Available: http://developerdrive.developerdrive.netdna-cdn.com/wp-content/uploads/2013/07/scroll-to-top.html. [Accessed: 10- Feb- 2015].
    setUpDynamicBackToTop = function () {
        var offset = 220,
                duration = 500;
        
        $(window).scroll(function () {
            if ($(this).scrollTop() > offset) {
                $('.back-to-top').fadeIn(duration);
            } else {
                $('.back-to-top').fadeOut(duration);
            }
        });

        $('.back-to-top').click(function (event) {
            event.preventDefault();
            $('html, body').animate({scrollTop: 0}, duration);
            return false;
        });
    };

    return {
        isValidVariable: isValidVariable,
        isValidNonEmptyString: isValidNonEmptyString,
        isValidFn: isValidFn,
        getPageContext: getPageContext,
        getCsrfToken: getCsrfToken,
        setUpDynamicBackToTop: setUpDynamicBackToTop
    };
}());

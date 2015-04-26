/* Used for global document, window and $ */


/*
 * Share Pinboard on Facebook
 */
$(function (d, s, id) {
    /*
     * Use of "use strict"; to define that JavaScript code should be executed in "strict mode" undeclared variables cannot be used..
     *  W3schools.com, 'JavaScript "use strict"', 2015. [Online]. Available: http://www.w3schools.com/js/js_strict.asp. [Accessed: 10- Feb- 2015].
     */

    "use strict";
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {
        return;
    }
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.0";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

/*
 * Share Pinboard on Twitter
 */
$(function (d, s, id) {

    /*
     * Use of "use strict"; to define that JavaScript code should be executed in "strict mode".
     * W3schools.com, 'JavaScript "use strict"', 2015. [Online]. Available: http://www.w3schools.com/js/js_strict.asp. [Accessed: 10- Feb- 2015].  
     */
    "use strict";
    var t, js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {
        return;
    }
    js = d.createElement(s);
    js.id = id;
    js.src = "https://platform.twitter.com/widgets.js";
    fjs.parentNode.insertBefore(js, fjs);
    return window.twttr || (t = {_e: [], ready: function (f) {
            t._e.push(f);
        }});
}(document, "script", "twitter-wjs"));

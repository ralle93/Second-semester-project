/**
 * @Author Tommy Troest.
 */

function galleryRotation (){
    if (window.location == window.parent.location) {
        this.window.location = 'index.html';
    }
}

function cakeObjectExpandOnClick() {
    ("#product-image-1").click(function() {
        ("#product-description-1").toggle()
    });
}

var iframe = document.getElementById('iframe');

fadeOut(iframe, 1000);

function fadeOut(el, duration) {

    /*
     * @param el - The element to be faded out.
     * @param duration - Animation duration in milliseconds.
     */

    var step = 10 / duration,
        opacity = 1;
    function next() {
        if (opacity <= 0) { return; }
        el.style.opacity = ( opacity -= step );
        setTimeout(next, 10);
    }
    next();
}
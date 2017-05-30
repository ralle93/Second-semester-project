/**
 * Tommy Troest.
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

var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("dot");

    if (n > slides.length) {
        slideIndex = 1
    }

    if (n < 1) {
        slideIndex = slides.length
    }

    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }

    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }

    slides[slideIndex-1].style.display = "block";
    dots[slideIndex-1].className += " active";
}
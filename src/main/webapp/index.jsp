<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!--Start Librerias de Bootstrap-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <!--<link rel="stylesheet" href="complements/bootstrap/bootstrap_3.4/css/bootstrap.min.css">-->
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="library/bootstrap_3.4/js/jquery.min.js">
    <link rel="stylesheet" href="library/bootstrap_3.4/js/bootstrap.min.js">
    <!--End Librerias de Bootstrap-->

    <!--Start Librerias Propias de Jonas Schmedtmann-->
    <%--<link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">--%>

    <link rel="stylesheet" href="complements/css/style.css">
    <link rel="shortcut icon" type="image/png" href="complements/img/favicon.png">
    <!--End Librerias Propias de Jonas Schmedtmann-->

    <title>Inicio Pvsoft</title>

    <!-- Estilos Propios -->
    <link href="complements/css/own_style.css" rel="stylesheet" type="text/css">

</head>

<body>

<div class="navigation">
    <input type="checkbox" class="navigation__checkbox" id="navi-toggle">

    <label for="navi-toggle" class="navigation__button">
        <span class="navigation__icon">&nbsp;</span>
    </label>

    <div class="navigation__background">&nbsp;</div>

    <nav class="navigation__nav">
        <ul class="navigation__list">
            <li class="navigation__item"><a href="pages/jsp/building.jsp" class="navigation__link"><span>04</span>Conpany</a></li>
            <li class="navigation__item"><a href="practice/paul/example_0.html" class="navigation__link"><span>03</span>Contac us</a></li>
            <li class="navigation__item"><a href="/objectboard/signin" class="navigation__link"><span>01</span>Sign In</a></li>
            <li class="navigation__item"><a href="pages/jsp/sing_up.jsp" class="navigation__link"><span>02</span>Sign Up</a></li>
        </ul>
    </nav>
</div>

<%@include file="complements/jsp/header_index.jsp"%>

<main>
    <section class="section-about">
        <div class="u-center-text u-margin-bottom-big">
            <h2 class="heading-secondary">
                Take a look at the solutions we offer for you
            </h2>
        </div>

        <div class="row">
            <div class="col-1-of-2">
                <h3 class="heading-tertiary u-margin-bottom-small">You're going to fall in love with nature</h3>
                <p class="paragraph">
                    Lorem ipsum carrots enhanced rebates. Open, it wisely rejects any free painful consequences
                    And all the villages, which we regard the practice of which, except a.
                </p>

                <h3 class="heading-tertiary u-margin-bottom-small">Live adventures like you never have before</h3>
                <p class="paragraph">
                    Lorem ipsum carrots enhanced rebates. No harsh desert for pleasure.
                </p>

                <a href="library/bootstrap_3.7/pages/inicio.html" class="btn-text">Learn more &rarr;</a>
            </div>
            <div class="col-1-of-2">
                <div class="composition">

                    <img srcset="complements/img/nat-1.jpg 300w, complements/img/nat-1-large.jpg 1000w"
                         sizes="(max-width: 56.25em) 20vw, (max-width: 37.5em) 30vw, 300px"
                         alt="Photo 1"
                         class="composition__photo composition__photo--p1"
                         src="complements/img/nat-1-large.jpg">

                    <img srcset="complements/img/nat-2.jpg 300w, complements/img/nat-2-large.jpg 1000w"
                         sizes="(max-width: 56.25em) 20vw, (max-width: 37.5em) 30vw, 300px"
                         alt="Photo 2"
                         class="composition__photo composition__photo--p2"
                         src="complements/img/nat-2-large.jpg">

                    <img srcset="complements/img/nat-3.jpg 300w, complements/img/nat-3-large.jpg 1000w"
                         sizes="(max-width: 56.25em) 20vw, (max-width: 37.5em) 30vw, 300px"
                         alt="Photo 3"
                         class="composition__photo composition__photo--p3"
                         src="complements/img/nat-3-large.jpg">
                </div>
            </div>
        </div>
    </section>

    <section class="section-features">

        <div class="row">
            <div class="col-1-of-4">
                <div class="feature-box">
                    <i class="feature-box__icon icon-basic-world"></i>
                    <h3 class="heading-tertiary u-margin-bottom-small">Explore the world</h3>
                    <p class="feature-box__text">
                        Lorem ipsum carrots enhanced rebates. Open, edit it wisely.
                    </p>
                </div>
            </div>

            <div class="col-1-of-4">
                <div class="feature-box">
                    <i class="feature-box__icon icon-basic-compass"></i>
                    <h3 class="heading-tertiary u-margin-bottom-small">Meet nature</h3>
                    <p class="feature-box__text">
                        Lorem ipsum carrots enhanced rebates. Open, edit it wisely.
                    </p>
                </div>
            </div>

            <div class="col-1-of-4">
                <div class="feature-box">
                    <i class="feature-box__icon icon-basic-map"></i>
                    <h3 class="heading-tertiary u-margin-bottom-small">Find your way</h3>
                    <p class="feature-box__text">
                        Lorem ipsum carrots enhanced rebates. Open, edit it wisely.
                    </p>
                </div>
            </div>

            <div class="col-1-of-4">
                <div class="feature-box">
                    <i class="feature-box__icon icon-basic-heart"></i>
                    <h3 class="heading-tertiary u-margin-bottom-small">Live a healthier life</h3>
                    <p class="feature-box__text">
                        Lorem ipsum carrots enhanced rebates. Open, edit it wisely.
                    </p>
                </div>
            </div>
        </div>
    </section>

    <section class="section-tours" id="section-tours">
        <div class="u-center-text u-margin-bottom-big">
            <h2 class="heading-secondary">
                Most popular tours
            </h2>
        </div>

        <div class="row">
            <div class="col-1-of-3">
                <div class="card">
                    <div class="card__side card__side--front">
                        <div class="card__picture card__picture--1">
                            &nbsp;
                        </div>
                        <h4 class="card__heading">
                            <span class="card__heading-span card__heading-span--1">The Sea Explorer</span>
                        </h4>
                        <div class="card__details">
                            <ul>
                                <li>3 day tours</li>
                                <li>Up to 30 people</li>
                                <li>2 tour guides</li>
                                <li>Sleep in cozy hotels</li>
                                <li>Difficulty: easy</li>
                            </ul>
                        </div>
                    </div>
                    <div class="card__side card__side--back card__side--back-1">
                        <div class="card__cta">
                            <div class="card__price-box">
                                <p class="card__price-only">Only</p>
                                <p class="card__price-value">$297</p>
                            </div>
                            <a href="#popup" class="btn btn--white">Book now!</a>
                        </div>
                    </div>
                </div>
            </div>


            <div class="col-1-of-3">
                <div class="card">
                    <div class="card__side card__side--front">
                        <div class="card__picture card__picture--2">
                            &nbsp;
                        </div>
                        <h4 class="card__heading">
                            <span class="card__heading-span card__heading-span--2">The Forest Hiker</span>
                        </h4>
                        <div class="card__details">
                            <ul>
                                <li>7 day tours</li>
                                <li>Up to 40 people</li>
                                <li>6 tour guides</li>
                                <li>Sleep in provided tents</li>
                                <li>Difficulty: medium</li>
                            </ul>
                        </div>

                    </div>
                    <div class="card__side card__side--back card__side--back-2">
                        <div class="card__cta">
                            <div class="card__price-box">
                                <p class="card__price-only">Only</p>
                                <p class="card__price-value">$497</p>
                            </div>
                            <a href="#popup" class="btn btn--white">Book now!</a>
                        </div>
                    </div>
                </div>
            </div>


            <div class="col-1-of-3">
                <div class="card">
                    <div class="card__side card__side--front">
                        <div class="card__picture card__picture--3">
                            &nbsp;
                        </div>
                        <h4 class="card__heading">
                            <span class="card__heading-span card__heading-span--3">The Snow Adventurer</span>
                        </h4>
                        <div class="card__details">
                            <ul>
                                <li>5 day tours</li>
                                <li>Up to 15 people</li>
                                <li>3 tour guides</li>
                                <li>Sleep in provided tents</li>
                                <li>Difficulty: hard</li>
                            </ul>
                        </div>

                    </div>
                    <div class="card__side card__side--back card__side--back-3">
                        <div class="card__cta">
                            <div class="card__price-box">
                                <p class="card__price-only">Only</p>
                                <p class="card__price-value">$897</p>
                            </div>
                            <a href="#popup" class="btn btn--white">Book now!</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="u-center-text u-margin-top-huge">
            <a href="pages/jsp/building.jsp" class="btn btn--green">Discover all tours</a>
        </div>
    </section>

    <section class="section-stories">
        <div class="bg-video">
            <video class="bg-video__content" autoplay muted loop>
                <source src="complements/img/video.mp4" type="video/mp4">
                <source src="complements/img/video.webm" type="video/webm">
                Your browser is not supported!
            </video>
        </div>

        <div class="u-center-text u-margin-bottom-big">
            <h2 class="heading-secondary">
                We make people genuinely happy
            </h2>
        </div>

        <div class="row">
            <div class="story">
                <figure class="story__shape">
                    <img src="complements/img/nat-8.jpg" alt="Person on a tour" class="story__img">
                    <figcaption class="story__caption">Mary Smith</figcaption>
                </figure>
                <div class="story__text">
                    <h3 class="heading-tertiary u-margin-bottom-small">I had the best week ever with my family</h3>
                    <p>
                        Lorem ipsum carrots enhanced rebates. Open, it wisely rejects any free painful consequences
                        And all the villages, which we regard the practice of which, except a. Open, it wisely rejects free
                        the exercise of any one of all the cities that he may repel the consequences of dishonor, which except a.
                    </p>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="story">
                <figure class="story__shape">
                    <img src="complements/img/nat-9.jpg" alt="Person on a tour" class="story__img">
                    <figcaption class="story__caption">Jack Wilson</figcaption>
                </figure>
                <div class="story__text">
                    <h3 class="heading-tertiary u-margin-bottom-small">WOW! My life is completely different now</h3>
                    <p>
                        Lorem ipsum carrots enhanced rebates. Open, it wisely rejects any free painful consequences
                        And all the villages, which we regard the practice of which, except a. Open, it wisely rejects free
                        the exercise of any one of all the cities that he may repel the consequences of dishonor, which except a.
                    </p>
                </div>
            </div>
        </div>

        <div class="u-center-text u-margin-top-huge">
            <a href="pages/jsp/building.jsp" class="btn-text">Read all stories &rarr;</a>
        </div>
    </section>

    <section class="section-book">
        <div class="row">
            <div class="book">
                <div class="book__form">
                    <form action="pages/jsp/building.jsp" class="form" method="post">
                        <div class="u-margin-bottom-medium">
                            <h2 class="heading-secondary">
                                Start booking now
                            </h2>
                        </div>

                        <div class="form__group">
                            <input type="text" class="form__input" placeholder="Full name" id="name" required>
                            <label for="name" class="form__label">Full name</label>
                        </div>

                        <div class="form__group">
                            <input type="email" class="form__input" placeholder="Email address" id="email" required>
                            <label for="email" class="form__label">Email address</label>
                        </div>

                        <div class="form__group u-margin-bottom-medium">
                            <div class="form__radio-group">
                                <input type="radio" class="form__radio-input" id="small" name="size">
                                <label for="small" class="form__radio-label">
                                    <span class="form__radio-button"></span>
                                    Small tour group
                                </label>
                            </div>

                            <div class="form__radio-group">
                                <input type="radio" class="form__radio-input" id="large" name="size">
                                <label for="large" class="form__radio-label">
                                    <span class="form__radio-button"></span>
                                    Large tour group
                                </label>
                            </div>
                        </div>

                        <div class="form__group">
                            <button class="btn btn--green">Next step &rarr;</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</main>

<!--Start ################################ Footer ##############################-->

<%@include file="complements/jsp/footer_index.jsp"%>

<!--End ################################ Footer ##############################-->


<div class="popup" id="popup">
    <div class="popup__content">
        <div class="popup__left">GUAUU!GUAUU!
            <img src="complements/img/nat-8.jpg" alt="Tour photo" class="popup__img">
            <img src="complements/img/nat-9.jpg" alt="Tour photo" class="popup__img">
        </div>
        <div class="popup__right">
            <a href="#section-tours" class="popup__close">&times;</a>
            <h2 class="heading-secondary u-margin-bottom-small">Start booking now</h2>
            <h3 class="heading-tertiary u-margin-bottom-small">Important &ndash; Please read these terms before booking</h3>
            <p class="popup__text">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed tempor and vitality, so that the labor and sorrow, some important things to do eiusmod.
                However, the price of laughter. A salad recipe that will disappear. Antioxidants design that hatred weekend
                the mass of a lot of life. My bibendum neque egestas congue. Invest any clinical nutrition for soccer
                let it be. But no one ever graduated weekend diameter of the blockage. Pellentesque development
                When pregnant Pulls submissions. Proin fermentum laoreet condimentum neque leo velit dolor, the gate was not.
                Pregnant sit Curabitur sit is always a tomorrow. Employee at the element Eu but who hate soccer. shop
                no, nor the laughter of vitae aliquet eget ullamcorper sit amet. For free Tech is. mainstream mass
                peanut sauce or until the temperature of Westinghouse. A lot of great antioxidants. but financing
                euismod nisi at the gate.
            </p>
            <a href="pages/jsp/building.jsp" class="btn btn--green">Book now</a>
        </div>
    </div>
</div>

</body>
</html>
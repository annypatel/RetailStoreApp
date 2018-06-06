# RetailStoreApp

RetailStoreApp is a sample app that I developed as part of one of the interview process back in November 2016. Since then lot of things has changed in Android Development. This app uses Model-View-Presenter pattern to separate business logic from Activity/Fragment, in combination with Repository pattern and Dagger 2 to wire everything together.

RetailStoreApp allows customers browse products by category and create a cart with products that they
wish to purchase. Categories and Products data is hardcoded inside the app in SQL format in **mock** sourceset inside the assets folder.

## Android Development
RetailStoreApp uses following libraries:

* Dagger 2
* Room
* RxJava & RxAndroid
* Picasso
* Data Binding
* Android Support Libraries (appcompat, design, cardview, recyclerview)
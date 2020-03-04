# ShoppingApp
 
This app uses following components:

Dagger2

MVVM Architecture

Room Database

RXJava/Android

Mockito for test

UI :

UI layer is extracted into 3 parts: 

1.Fragment:Its role is to register listener and pass events to corresponding views.
           
2.Implementation: This is the veiw where Inflation and rendering of view happens.There is no chain of dependencies from Fragment to   FragmentImpl.This allows us to demostrate Dependency Inversion Principle of SOLID principle.This allows to swap different implementations of SpecificViewMvc very easily, which can be extremely beneficial (e.g. AB testing of different implementations of a screen) and makes it more readable.
           
3.View: Interface which contains Listeners and common methods for view.
           
Design Patterns Used:
1. Observable design pattern: This is used heavily for any events. Fragments will recieve events only if it is registered. 
            
2. Factory design pattern:  Fragment creation happens at runtime based on the classname passed.
                
  Room: 
        App uses Room database to store Wishlist Data. 
        
  Repository:
        App uses shopping repository as single point of truth for webservice and database management.
        
  RX:
       RX is used for multithrading for POST, GET, DELETE and GET. This is used in combination with Retrofir.(ShoppingService.class)
        
  Dagger 2:
       App uses Dagger2 as dependency library.




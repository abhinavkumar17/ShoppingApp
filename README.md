# ShoppingApp
 
This app uses following components:

Dagger2

MVVM Architecture

Room Database

RXJava/Android

Mockito for test

UI :

UI layer is extracted into 3 parts: 

           Fragment:Its role is to rgister listener and pass events to corresponding views.
           
           Implementation: This is the veiw where Inflation and rendering of view happens.
           
           View: Interface which contains Listeners and common methods for view.
           
Design Patterns Used:
            1. Observable design pattern: This is used heavily for any events. Fragments who are registered for receiving events have to register before. 
            
            2. Factory design pattern
            
            3. Repository
            
     




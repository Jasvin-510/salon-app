
Run the code:-
    connect android studio with firebase.
       tools->firebase->authentication
       tools->firebase->firestore
       tools->firebase->storage
    for run use android phone with developer mode on, or use virtual devise provided by Android Studio.

All the screens:-
 2files for all screen 1st java(code),2nd xml(layout code)

sort description of all file:-

1) MainActivity.java  -> 2 buttons 1st button for client and 2nd for salon

client screens:-

2) login_client.java  -> client login,forgot password,link for registration

3) register_client.java -> client registration

4) client_homepage.java -> salon list using recycler view,menu,filter 
           object_adapter.java -> adapter for recycler view
           object.java -> object use in recycler view         
 
5) client_saloon_info.java -> saloon info,place order

6) client_filter.java  -> filter(men,women,both(unisex))

7) client_menu_1.java  -> menu(history,rating,change profile,logout)

8) client_history.java -> client past order using recyclerview
           client_history_adapter.java -> adapter for recycler view
           object2.java -> object use in recycler view 
               

9) client_rating.java  -> Former orders which are not rated by client using recycler view
           client_rating_adapter.java -> adapter for recycler view
           object2.java -> object use in recycler view 

10) give_rating.java -> order info,give rating

11) client_update_info.java -> change profile info

salon screens:-

12) login_saloon.java -> salon login,forgot password,link for registration

13) register_saloon.java -> salon registration,upload goverment verified document

14) saloon_homepage.java -> client request(order) using recycler view,menu
           saloon_request_adapter.java -> adapter for recycler view
           object2.java -> object use in recycler view 

15) saloon_homepage2.java -> order detail,complete,cancelled

16) saloon_menu_1.java -> menu(history,change profile,logout)

17) saloon_history.java -> salon past order using recycler view
           saloon_history_adapter.java -> adapter for recycler view
           object2.java -> object use in recycler view 

18) saloon_update_info.java -> change salon info,change price for service,add remove service

19) timepass1.java -> not used in app,just for experiment



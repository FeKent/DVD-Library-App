# DVD-Library-App

A CRUD Android App to display and filter through a DVD collection, providing users with a poster and overview of each film.

# Description

This application allows you to create, read, update, and display data about a film. This includes the: title, runtime, a poster, an overview, year, genre, and starring actors of each film added. It has three main screens: Intro, Film, and Add.

---
### Intro Screen

  - On the "Intro Screen" you have:
    - A list of films - with genre icons on either side of the title
      - A single tap takes you to the "Film Screen" that has all the film's details
      - A double tap allows you to delete a film from the database
      - A long press allows you to edit the details of the film 
    - A search bar that can be used to filter the list of films according to title, year, genre, and starring actors
    - A sorting feature that allows you to change how the list of films is displayed according to title, genre, year, runtime, and date added, in both ascending and descending order
    - An add button that takes you to the "Add Screen"
    - A tracker that displays the current number of films within the database
    - Example:
   
![image](https://github.com/SnippyRex/DVD-Library-App/assets/118063936/939b9b34-55c2-4d57-8d42-f671e2d0b9e1)![image](https://github.com/SnippyRex/DVD-Library-App/assets/118063936/242737bc-7e42-4e2c-a5cf-ab1c196f75c6)


---

### Film Screen / Details Screen
  - On the "Film Screen" you have:
    - All the details about the film displayed
      - Tapping on the film's title will take you back to the "Intro Screen" as well as pressing the back button
    - A prominent poster of the film itself
      - Tapping on this poster will reveal the film's overview
    - Example:
   
     
![image](https://github.com/SnippyRex/DVD-Library-App/assets/118063936/d5be177a-960a-4324-a627-cd66b4f4afe6)![image](https://github.com/SnippyRex/DVD-Library-App/assets/118063936/20ceff63-2479-477e-8a49-911614ffe280)

---

### Add Screen
     
  - On the "Add Screen" you have:
    - Several TextFields that **require** user input before allowing a film to be saved
    - The Genre fields have DropDown Menus, though a film doesn't require a second genre
    - A back button that will take you to the "Intro Screen"
    - An add button that will save the film you just added
      - This will also create an API request and response to attain the film poster and overview
    - This is also the same screen that will come up when editing a film, however, the TextFields will be filled in with the details that already exist 
    - Example:
   
![image](https://github.com/SnippyRex/DVD-Library-App/assets/118063936/77989465-6ac4-4122-8f9e-a1c038106f5c)![image](https://github.com/SnippyRex/DVD-Library-App/assets/118063936/008b00ea-9ed5-43ab-9850-a33507dc4226)

---
   
# Technologies Used

- Several dependencies were used (Material 3, Room, Moshi, Retrofit, Coroutines, etc.)
- Jetpack Compose and Material 3 were used to create the UI
- Room was used to make and use the film Database
- Moshi and Retrofit were used to make the API service
- Compose Navigation was used to control the app navigation
- Coroutines were used to execute database functions and API calls asynchronously
- ViewModel was used to separate logic from the composables in the app navigation
- A Flip Card was used to animate the poster revealing the overview on the flip side of the card
- [RevealSwipe](https://github.com/ch4rl3x/RevealSwipe/tree/main) library was used to make the Update & Delete film functions more user friendly


---

# Acknowledgements

- The API used: https://www.themoviedb.org/
- Genre Icons were sourced from: https://icons8.com
















<h1 align="center">NoteData</h1>
<p align="center">This is Ktor backend server for NoteApp</p>

<details open ="open">
  <summary>Contains</summary>
  <ol>
    <li>
      <a href='#about-the-project'>About Project</a>
        <ul>
          <li><a href="#built-with">Built With</a></li>
        </ul>
    </li>
    <li>
      <a href='#getting-started'>Getting Started</a>
        <ul>
          <li><a href="#prerequisites">Prerequisites</a></li>
        </ul> 
        <ul>
          <li><a href="#installation">Installation</a></li>
        </ul>
    </li> 
     <li>
      <a href='#usage'>Usage</a>
    </li>
      <li>
      <a href='#screenshots'>Screenshots</a>
    </li>
    <li>
      <a href='#contacts'>Contacts</a> 
    </li>
  </ol>
</details>

## About Project

This is Ktor backend server for [NoteApp](https://github.com/OzolsUgis/NoteApp.git)

This server :
*  Encrypts your password using Hash and Salt
*  Provides basic authentication

### Built With 

* [Ktor](https://ktor.io/)
* [MongoDB](https://www.mongodb.com/cloud/atlas/lp/try2?utm_source=google&utm_campaign=gs_footprint_row_search_core_brand_atlas_desktop&utm_term=mongodb&utm_medium=cpc_paid_search&utm_ad=e&utm_ad_campaign_id=12212624584&gclid=Cj0KCQjwqKuKBhCxARIsACf4XuHnZLwMpGlVEMK6aKnUeCRLZhzG9S2jNUJwbTMP0Rtl55KA5sbe_MAaAoJ9EALw_wcB)
* [Courotines](https://developer.android.com/kotlin/coroutines?gclid=EAIaIQobChMIqZC4jo-i8gIVsAZ7Ch1rOASzEAAYASAAEgKAwvD_BwE&gclsrc=aw.ds)
* [Commons Codec](https://github.com/apache/commons-codec)

## Getting Started
### Prerequisites 

You need to install IntelliJ IDEA Version: 2021.2.2, you can download it here : 

* [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)

### Installation 
1. You need to clone Backend server in IntelliJ **file -> New -> Project from version control...** And enter this https://github.com/OzolsUgis/NoteData.git in URL

2. Try to run application. NoteData -> src -> Application.kt -> fun Main

If You get this response 

![product-screenshot](https://live.staticflickr.com/65535/51504712575_3b39838725.jpg)

It means your Port is already active, you need to specify different Port you can do that in NoteData ->  resources -> application.conf

  ```kotlin
          deployment {
        port = // add different port here for example 8001
        port = ${?PORT}
    }
  ```
  
  
  If you get this response 
  
  ![product-screenshot](https://live.staticflickr.com/65535/51503773861_6dd38986e2.jpg)
  
  It means your server is up and running
  
  ## Usage
  
  You can use MongoDB compass to review your database You can download it here [MongoDB Compass](https://www.mongodb.com/products/compass)
  
  And Postman to make requests [Postman](https://www.postman.com/)
  
   ## Screenshots
   
   Successfully created account via Postman 
   
   ![product-screenshot](https://live.staticflickr.com/65535/51503831451_73318dc054_z.jpg)
   
   
   Account saved in database 
   
   ![product-screenshot](https://live.staticflickr.com/65535/51503831731_9c980f1655_z.jpg)
   
   
  ## Contacts

Ugis Ozols - (This will follow)

Project Link (NoteApp)- https://github.com/OzolsUgis/NoteApp.git

Project Link (NoteData) - https://github.com/OzolsUgis/NoteData.git

  

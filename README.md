# A user account API
with a view for displaying users
<br>  -POST returns JSON with JWT Token /login
<br>  -GET return user in JSON format /users/{id}
<br>  -GET return all users in JSON format /users?page=0&size=5&sortBy=id
<br>  -POST add a new user to db (requires JSON body) /users/new
<br>  -PUT update a user in db (requires JSON body) /users/{id}/update
<br>  -GET returns a view with a table of all users /view/users?page=0&size=5&sortBy=id
<br>  -GET returns a view of a selected user /view/users/{id}?page=0&size=5&sortBy=id 
<h3> requirements </h3>
-Install PostgreSQL
<br>  -Start the PostgreSQL server
<br>  -Create a DB named user-app

# Whalespotting

[//]: # (TODO 409: Update this from Python)
1. Fork this repo
    * Go to this repo's GitHub page ({url here})
    * Click `Fork` in the top-right of the page - this will create a copy of this repo in **your own GitHub account**

2. Clone (download) the repo
    * Go to your newly-created fork of the repo (on GitHub).
    * Click `Clone or download` (the green button on the right).
    * Make sure the page says `Clone with SSH` (rather than `Clone with HTTPS`).
    * Open your git client (e.g. GitKraken) and use this link to clone the repo.  
      Your trainer will able to help you with this.

3. "Cloning the repo" will create a folder on your computer with the files from this repo.  
   Open this folder in IntelliJ / Visual Studio Code.

4. Open a command-prompt in this same folder.  
   Your trainer can show you how to do this, if you need any help.

[//]: # (5. Run this command to set up the necessary dependencies:  )

[//]: # (   `poetry install`)

[//]: # ()
[//]: # (6. Copy the `.env-template` file to a new `.env` file. This `.env` file shouldn't ever be committed &#40;and is already included in the `.gitignore`&#41; as it may contain secrets.)

[//]: # ()
[//]: # (7. Make sure you've got PostgreSQL installed. You'll notice that your `.env` file contains POSTGRES config variables. As well as installing Postgres we need to make sure we have a user set up to match that config, with the right permissions to create the database when the app runs. All instructions for this step are in the `Install Postgres` section below.)

[//]: # ()
[//]: # (8. Run this command to run your code:  )

[//]: # (   `poetry run flask run`)

## Setting up Postgres

Before you run the app you will need to make sure you've got Postgres installed and a database set up.

### Install Postgres

1. Download and install the [PostgreSQL server software](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) if you haven't already.

2. Make sure you've added Postgres to your PATH (`C:\Program Files\PostgreSQL\<your version number>\bin`).

3. Open the Windows Start menu and search for "pgAdmin". When you start "pgAdmin" for the first time, you'll be asked to set a password for your superuser. You'll need this password in a moment on step 4.

### Set up Whalespotting user

1. Open a terminal and type `psql -U postgres` - `postgres` is the username of your superuser that you just set the password for. You should be prompted to enter that password, and then see the terminal prompt `postgres=#`. We can now start running some SQL from the terminal.

2. We don't want to run our app with our superuser, so we're going to create a new user just for Whalespotting. To make things simpler, the `.env` file is set up with `whalespotting` set as the server, database, username and password, so we need to set up to match this.

3. From the terminal, type `\du` to see your current list of Postgres users. It's likely you'll just have one (your `postgres` superuser).

4. Now type, `CREATE USER whalespotting WITH PASSWORD 'whalespotting';`. Make sure not to miss the `;` off the end of your SQL commands.

5. If you type `\du` again you should see a new user - `whalespotting` - has been created. They still don't have any privileges though, so they can't create a database, which we need to do for the app to run. To give the user privileges type `ALTER USER whalespotting WITH CREATEDB`. If you run `\du` one last time you should now be able to check that your user `whalespotting` has the `Create DB` role attribute listed next to it.

### Set up Whalespotting server

Next we need to set up a server instance. You can create one in "pgAdmin" by following these steps:

1. Click 'Add new server' in Quick Links

2. Fill out the required information for the new server instance.
   - General | name: `whalespotting`
   - Connection | Host name: `localhost`
   - Connection | Username: `whalespotting`
   - Connection | Password: `whalespotting`
   - Leave everything else as the default values (Port, Maintenance database etc.)

3. Click the "Save" button to create the server instance.

4. Start the server instance by right-clicking on it in the object browser and selecting "Start/Restart Server".

### Set up Whalespotting database

Lastly you'll need to set up a Whalespotting database. This can also be done from within "pgAdmin":

1. From within the Browser sidebar, navigate to the `whalespotting` server you just set up. Open the dropdown and right-click `Databases` > `Create` > `Database`.

2. In the `Create - Database` modal, fill out:
   - Database: `whalespotting`
   - Owner: `whalespotting` (this is the user you set up above)
   - Leave all other values as default.

3. Click `Create`.

## Migrations

[//]: # (TODO 409)

## Existing Whalespotting app users

[//]: # (TODO 409)
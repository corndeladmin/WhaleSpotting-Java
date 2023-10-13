# Whalespotting

## Getting Started

1. Fork this repo
    * Click `Fork` in the top-right of the page - this will create a copy of this repo in **your own GitHub account**

2. Clone (download) the repo
    * Go to your newly-created fork of the repo (on GitHub).
    * Click `Clone or download` (the green button on the right).
    * Make sure the page says `Clone with SSH` (rather than `Clone with HTTPS`).
    * Open your git client (e.g. GitKraken) and use this link to clone the repo.  
      Your trainer will able to help you with this.

3. "Cloning the repo" will create a folder on your computer with the files from this repo.  
   Open this folder in VSCode.

4. Open a command-prompt in this same folder.  
   Your trainer can show you how to do this, if you need any help.

5. Follow the instructions in the `Setting up Postgres` section below to ensure that PostgreSQL is installed and setup correctly. (You'll notice that your `application.properties` file contains some config variables prefixed `spring.datasource`. We need to make sure we have a Postgres user set up to match that config, with the right permissions to create the database when the app runs.)

6. Run `./gradlew run` in the console.

7. You should now be able to find your code running at: `http://localhost:8080/`

## Setting up Postgres

Before you run the app you will need to make sure you've got Postgres installed and a database set up by following the instructions below.

### Install Postgres

1. Download and install the [PostgreSQL server software](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) if you haven't already.

2. Open the Windows Start menu and search for "pgAdmin". When you start "pgAdmin" for the first time, you'll be asked to set a master password.

### Set up Whalespotting user

1. Inside your PostgreSQL server in pgAdmin, right-click on *Login/Group Roles* and create a new Login/Group Role with the name `whalespotting` (in the *General* tab), the password `whalespotting` (in the *Definition* tab) and the ability to log in and create databases (in the *Privileges* tab).

2. Click `Save` to create the user.

### Set up Whalespotting database

1. Inside your PostgreSQL server in pgAdmin, right-click on Databases and create a new Database with the name `whalespotting` and the owner `whalespotting` (both in the General tab).

2. Click `Save` to create the database.

## Existing admin user

There is an admin user already set up for you in the database (add in migration `V2__added_admin_member.sql`):

```
username: admin
password: adminpassword

role: ADMIN
```

You should be able to create more users by running the app and visiting `http://localhost:8080/register` or adding them straight to your database.

***

## Style Guide

https://www.oracle.com/technetwork/java/codeconventions-150003.pdf

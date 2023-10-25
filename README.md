# Virtual Power Plant

## Repository
https://github.com/NLCProject/VPP

## Installation
### Database
Install any SQL database on your local machine. This project was tested with MYSQL
(https://dev.mysql.com/downloads/mysql/). The following description is based on MYSQL.

+ During installation, create an user with name 'user' and password 'password'. If you want to use different
  credentials, change it in file 'application.properties' in fields 'spring.datasource.username' and
  'spring.datasource.password' too.
+ After installation, open the 'MySQL 8.0 Command Line Client' terminal and login as root.
+ Create the database with command 'CREATE DATABASE vpp'. If you want to use different
  database name, change it in file 'application.properties' in field 'spring.datasource.url'.
+ Tables are created by the Spring framework automatically when the application is started.

## Frontend
The VPP application has a frontend application to display and control the connected batteries

+ The first step is to install all dependencies. Go to the location '.\Gateway\internal-frontend'
+ Enter command 'npm install --save --legacy-peer-deps'.
+ In order to start the frontend, enter the command 'ng serve'
+ If the frontend application is running, enter the URL 'localhost:4200' in the browser. Since it is an Angular
  application, it is highly recommended to use Chrome to avoid any display errors.
+ Watch out to use different ports when starting the Gateways too. In order to start the
  application on a different port, use the command 'ng serve --port 4202'.

## Database Configuration
By default, the database is cleared and created new at every startup. To avoid this, a change configuration has to be
done in the file 'application.properties'. Change the following files to persist the data in the database after a
restart:
+ spring.jpa.hibernate.ddl-auto=none
+ spring.datasource.initialization-mode=never

## Application
The application is divided in different modules. The 'Core' module contains the entry point of the application.
You can start the application via the file 'Application.kt'. But make sure you installed the database first.

### Server Port
This application starts on port 8086. As user this is not that important, since the frontend connects with it
automatically. In case the port is already used, it can be changed in file 'application.properties' and field
'server.port'. Additionally, the server port needs to be changed in the frontend too. The port is defined in the file
'rest-header.service.ts'

### REST interface
For passive communication with the frontend and the virtual power plant, this application provides a REST interface.
The controllers are available via the configured server port and defined in the '*Controller.kt' classes.

### Multi-Start
Only one instance of a virtual power plant is allowed.

## Communication with the gateway
Actually it is required, that the virtual power plant has to be started first. When this application starts, it waits 
for any connecting gateway instances.

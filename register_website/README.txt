Author: Patsianotakis Charalampos - Undergraduate Student of University of Thessaly - Electrical and Computer Engineering Department.

This project is uses ubuntu OS and is supported with a mysql-server version 8.0.18.

In order to install the mysql-server follow the instructions:

Step 1: Download MySQL Repositories:

	wget -c https://dev.mysql.com/get/mysql-apt-config_0.8.11-1_all.deb

Step 2: Install the MySQL Repositories:

	sudo dpkg –i mysql-apt-config_0.8.10-1_all.deb

	--> Leave the default settings and click OK

Step 3: Install MySQL

	sudo apt-get update
	sudo apt-get install mysql-server

	--> The installation will prompt you to enter and confirm a root user and password for the MySQL database. Set a password "12345678"
	--> Next, the installer will display a notice about a new authentication method. Click OK, then on the next screen select the authentication method you want to use, then click OK again.

Step 4: Set up MySQL Security

	sudo mysql_secure_installation

	--> Set the password to low difficulty (0)
	--> Press everywhere else yes.

Step 5: Start, Stop, or Check Status of MySQL Service

	--> Start mySQL service:
	sudo service mysql start

	--> Stop mySQL service:
	sudo service mysql stop

	--> Verify MySQL is running:
	sudo service mysql status

Step 6: Launch MySQL to Enter Commands

	sudo mysql -u root -p
	--> The system should prompt for a password, then give an introduction to the MySQL shell. The command prompt will change to look like this: mysql>

Step 7: Check the existed databases

	SHOW DATABASES;

Step 8: Create the database to be connected with the code:

	
	CREATE DATABASE myDB;

REFERENCES:
[1]: https://phoenixnap.com/kb/how-to-install-mysql-on-ubuntu-18-04#htoc-step-1-download-the-mysql-repositories
[2]: http://www.mysqltutorial.org/mysql-create-database/
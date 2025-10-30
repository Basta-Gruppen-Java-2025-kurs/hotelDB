# hotelDB

## Database Structure

<img width="368" height="355" alt="image" src="https://github.com/user-attachments/assets/e60c1eba-bd6f-49cb-865d-4190f39123f4" />

## Class Structure

<img width="960" height="799" alt="image" src="https://github.com/user-attachments/assets/596aa701-b297-4614-8829-78ce9bd29746" />

## Database driver

* In order for the Database to work, you need to add the database driver to the project:

<img width="1018" height="224" alt="image" src="https://github.com/user-attachments/assets/b0431ace-e57b-4482-a538-dc5dc62f40dc" />

## Setting up MySQL user and password for local server

* In IntelliJ IDEA, select **Edit Configurations** from dropdown menu in the top right side

<img width="552" height="122" alt="image" src="https://github.com/user-attachments/assets/561932ba-3cc7-40b4-81dd-fca464524542" />

* Select **Add new run configuration...**
* From the dropdown, select **Application**
* For the new configuration, enter a name (e.g. HotelDB)
* Select main class (**Main**)
* Click the folder icon in the **Environment variables** field

<img width="799" height="349" alt="image" src="https://github.com/user-attachments/assets/87e507e8-502a-4dec-9bab-91e8d6f2a2fe" />

* Select the **mysql.env** file in *hotelDB* folder and click **OK**

<img width="426" height="309" alt="image" src="https://github.com/user-attachments/assets/d21c23d6-7c4d-4023-b70c-33723a768ad7" />

* Edit the **mysql.env** file: set the `MYSQL_USER` to your MySQL user name, and `MYSQL_PASSWORD` to MySQL user's password

<img width="484" height="437" alt="image" src="https://github.com/user-attachments/assets/c7c74964-86da-4d18-89e2-e45feb3e2cbb" />

* In the terminal, run `git update-index --skip-worktree mysql.env`, so your edits to the user and password don't get pushed to the repository.

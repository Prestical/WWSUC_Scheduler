# WWSUC_Scheduler v1.0

Program creates a schedule that shows when will someone use the computer

## Installation 

1. Check that is Java installed in your computer :
```bash
java --version
```
2. Clone the repo :
```bash
git clone https://github.com/Prestical/WWSUC_Scheduler.git
```

## Starting Program

Open the file in your terminal and write this code 

```bash
java -jar WWSUC_Scheduler.jar
```

## Usage

You can create schedule with this program. But first you have to upload a .txt file in this format : 

### Example
```txt
#Name
-Monday 9:00 10:00 11:00 12:00 13:00 14:00 15:00 16:00 17:00 18:00 19:00
-Tuesday 9:00 10:00 11:00 12:00 13:00 14:00 15:00 16:00 17:00 18:00 19:00
-Wednesday 9:00 10:00 11:00 12:00 13:00 14:00 15:00
-Thursday 9:00 10:00 11:00 12:00 13:00 14:00 15:00
-Friday 9:00 10:00 11:00 12:00 13:00 14:00 15:00
-Saturday 9:00 10:00 11:00 12:00 13:00 14:00 15:00 16:00 17:00
-Sunday 9:00 10:00 11:00 12:00 13:00 14:00 15:00 16:00 17:00
#Name2
...
```
This .txt file contains busy days and hours informations for program. Day range is from Monday to Sunday, Hour range is from 9:00 to 19:00 as shown in the example.

You can upload file with these steps `Upload` -> `Upload File` -> `Select your file from folders` -> `Ok`

After uploading the file you can see the user informations are applied in `Go to` -> `Users` section in menubar.

![UserPane_Info](https://github.com/user-attachments/assets/93ca4fd5-0857-4d7a-82a3-78b5f46f0951)


You can edit the informations in `Go to` -> `Edit` section in menubar.
1. Write the name that which user you want to edit.
2. Select a day of the week.
3. Select single or multiple busy hours for selected day.
4. Click Add Days button.
5. If you want to add busy hour for another day switch the selected day to what you want
6. Click Submit button

- Here is the example from `example-data.txt` :

![EditPane_Edit](https://github.com/user-attachments/assets/c6cf5a3a-c1a8-464a-b866-e6053cc17cd0)

- Then click the `Sort` button and see the result. Each time you click on the button, the program shows different variation of the sort

![TablePane_Sort](https://github.com/user-attachments/assets/d4a02751-efa7-41f9-a63e-cf4ce697cc88)

- If you want to save the table. You can follow these steps `Save` -> `Save as PNG` -> `Go to ./src/output/png`

![Output.png_In_File](https://github.com/user-attachments/assets/bed444c9-edc6-44b0-a214-554630382e91)



